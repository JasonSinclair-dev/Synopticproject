package me.jasonsinclair.musicarchivebackend.services;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StorageService {

    private final AmazonS3 space;

    public StorageService(){

        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials("DO004KG8BFNVN8JRKV6Z", "5buK/MlCB/XakDvLi1P/ymOEx8FDlweK9p3qYMzCXOc")
        );
        space = AmazonS3ClientBuilder
                .standard()
                .withCredentials(awsCredentialsProvider)
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration("ams3.digitaloceanspaces.com","ams3")
                )
                .build();
    }
//    Lists the object keys as a list of strings in the console
    public List<String> getSongFileNames(){

        ListObjectsV2Result result = space.listObjectsV2("musicarchivejason");
        List<S3ObjectSummary> objects = result.getObjectSummaries();

        return objects.stream()
//        Simplified with lambda reference
                .map(S3ObjectSummary::getKey).collect(Collectors.toList());

//                .forEach(s3ObjectSummary -> {
//                    System.out.println(s3ObjectSummary.toString());
//                });
    }
//    Upload files to the storage space3bucket
    public void uploadSong(MultipartFile file) throws IOException {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            space.putObject(new PutObjectRequest("musicarchivejason", file.getOriginalFilename(),file.getInputStream(), objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
        }
}
