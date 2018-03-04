/**
 *
 */
(function (window) {

    // var WORKER_PATH = 'recorderWorker.js';
    var WORKER_PATH = 'js/recorderjs/recorderWorker.js';

    var Recorder = function (source, cfg) {
        var config = cfg || {};
        var bufferLen = config.bufferLen || 4096;
        this.context = source.context;
        if (!this.context.createScriptProcessor) {
            this.node = this.context.createJavaScriptNode(bufferLen, 2, 2);
        } else {
            this.node = this.context.createScriptProcessor(bufferLen, 2, 2);
        }

        var worker = new Worker(config.workerPath || WORKER_PATH);
        worker.postMessage({
            command: 'init',
            config: {
                sampleRate: this.context.sampleRate
            }
        });
        var recording = false,
            currCallback;

        this.node.onaudioprocess = function (e) {
            if (!recording) return;
            worker.postMessage({
                command: 'record',
                buffer: [
                    e.inputBuffer.getChannelData(0),
                    e.inputBuffer.getChannelData(1)
                ]
            });
        }

        this.configure = function (cfg) {
            for (var prop in cfg) {
                if (cfg.hasOwnProperty(prop)) {
                    config[prop] = cfg[prop];
                }
            }
        }

        this.record = function () {
            recording = true;
        }

        this.stop = function () {
            recording = false;
        }

        this.clear = function () {
            worker.postMessage({command: 'clear'});
        }

        this.getBuffers = function (cb) {
            currCallback = cb || config.callback;
            worker.postMessage({command: 'getBuffers'})
        }

        this.exportWAV = function (cb, type) {
            currCallback = cb || config.callback;
            type = type || config.type || 'audio/wav';
            if (!currCallback) throw new Error('Callback not set');
            worker.postMessage({
                command: 'exportWAV',
                type: type
            });
        }

        this.exportMonoWAV = function (cb, type) {
            currCallback = cb || config.callback;
            type = type || config.type || 'audio/wav';
            if (!currCallback) throw new Error('Callback not set');
            worker.postMessage({
                command: 'exportMonoWAV',
                type: type
            });
        }

        worker.onmessage = function (e) {
            var blob = e.data;
            currCallback(blob);
        }

        source.connect(this.node);
        this.node.connect(this.context.destination);   // if the script node is not connected to an output the "onaudioprocess" event is not triggered in chrome.
    };

    Recorder.setupDownload = function (blob, filename, soundFileName) {



        /*	  var xhr = new XMLHttpRequest();
              xhr.open('POST', 'upload', true);
              xhr.onload = function(e) { console.log("loaded"); };
              xhr.onreadystatechange = function(){
                  console.log("state: " + xhr.readyState);
              };
              // Listen to the upload progress.
            //  xhr.upload.onprogress = function(e) { console.log("uploading..."); };
              xhr.setRequestHeader("Content-Type");
              xhr.send(blob);*/


        var url = (window.URL || window.webkitURL).createObjectURL(blob);
        var link = document.getElementById("save");

        link.href = url;
        link.download = filename || 'output.wav';
        var xhr = new XMLHttpRequest();
        var formDta = new FormData();
        xhr.open('POST', 'upload', true);
        xhr.onload = function (e) {
            console.log("loaded");
        };
        xhr.onreadystatechange = function () {
            console.log("state: " + xhr.readyState);
        };

        formDta.append("soundFileName", soundFileName);
        formDta.append("blob", blob);

        //xhr.setRequestHeader("Content-Type", "multipart/form-data");

        xhr.send(formDta);
    }

    window.Recorder = Recorder;

})

/*function upload(blob) {
  var xhr = new XMLHttpRequest();
  xhr.open('POST', '/upload', true);
  xhr.onload = function(e) { console.log("loaded"); };
  xhr.onreadystatechange = function(){
      console.log("state: " + xhr.readyState);
  };
  // Listen to the upload progress.
  xhr.upload.onprogress = function(e) { console.log("uploading..."); };
 // xhr.setRequestHeader("Content-Type", "video/webm");
  xhr.send(blob);
}*/

(window);