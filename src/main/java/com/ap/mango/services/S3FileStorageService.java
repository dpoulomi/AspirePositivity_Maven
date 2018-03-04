package com.ap.mango.services;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class S3FileStorageService {


    private static final String SUFFIX = "/";
    private static final String YourAccessKeyID = "AKIAI365RCPTYN3NMZIA";
    private static final String YourSecretAccessKey = "w88+A22Byxwz3h9is3w5wXUYpDAOl5EkpKo866Z+";


  //  public void fileUploadToS3(final InputStream inputStream) {
  public static void main(final String[] args) {


        // credentials object identifying user for authentication
        // user must have AWSConnector and AmazonS3FullAccess for
        // this example to work
        final AWSCredentials credentials = new BasicAWSCredentials(YourAccessKeyID,
                YourSecretAccessKey);

        // create a client connection based on credentials
      //  final AmazonS3 s3client = new AmazonS3Client(credentials);


      final AmazonS3 s3client = AmazonS3ClientBuilder.standard()
              .withCredentials(new AWSStaticCredentialsProvider(credentials))
              .build();

        // create bucket - name must be unique for all S3 users
        final String bucketName = "new-buckets";

        s3client.createBucket(bucketName);

        // list buckets
        for (final Bucket bucket : s3client.listBuckets()) {
            System.out.println(" - " + bucket.getName());
        }

        // create folder into bucket
        final String folderName = "testfolder";
        createFolder(bucketName, folderName, s3client);

        // upload file to folder and set it to public
        final String fileName = folderName + SUFFIX + "song.wav";
        s3client.putObject(new PutObjectRequest(bucketName, fileName,
                new File("/Users/neo/personal/Poulomi Das_Resume.docx"))
                .withCannedAcl(CannedAccessControlList.PublicRead));

     //   s3client.putObject(new PutObjectRequest(bucketName, fileName,inputStream)

      //  deleteFolder(bucketName, folderName, s3client);

        // deletes bucket
     //   s3client.deleteBucket(bucketName);
    }

    public static void createFolder(final String bucketName, final String folderName, final AmazonS3 client) {
        // create meta-data for your folder and set content-length to 0
        final ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(0);
        // create empty content
        final InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
        // create a PutObjectRequest passing the folder name suffixed by /
        final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
                folderName + SUFFIX, emptyContent, metadata);
        // send request to S3 to create folder
        client.putObject(putObjectRequest);
    }

    /**
     * This method first deletes all the files in given folder and than the
     * folder itself
     */
    public static void deleteFolder(final String bucketName, final String folderName, final AmazonS3 client) {
        final List fileList =
                client.listObjects(bucketName, folderName).getObjectSummaries();
     /*   for (final S3FileStorageService file : fileList) {
            client.deleteObject(bucketName, file.getKey());
        }*/
        client.deleteObject(bucketName, folderName);
    }
}

