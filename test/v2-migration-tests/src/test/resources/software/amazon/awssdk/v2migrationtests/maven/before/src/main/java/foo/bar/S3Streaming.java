/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package foo.bar;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

public class S3Streaming {

    AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();

    void getObject(String bucket, String key) throws Exception {
        S3Object s3Object = s3.getObject(bucket, key);
        s3Object.getObjectContent().close();
    }

    void putObject_bucketKeyContent(String bucket, String key, String content) {
        s3.putObject(bucket, key, content);
    }

    void putObject_bucketKeyFile(String bucket, String key, File file) {
        s3.putObject(bucket, key, file);
    }

    /**
     * Mixed ordering to ensure the files are assigned correctly
     */
    void putObject_requestPojoWithFile(String bucket, String key) {
        File file4 = new File("file4.txt");
        File file3 = new File("file3.txt");
        File file1 = new File("file1.txt");
        File file2 = new File("file2.txt");

        PutObjectRequest request1 = new PutObjectRequest(bucket, key, file1);

        PutObjectRequest request2 = new PutObjectRequest(bucket, key, "location");
        request2.setFile(file2);

        s3.putObject(new PutObjectRequest(bucket, key, "location").withFile(file3));
        s3.putObject(request2);
        s3.putObject(new PutObjectRequest(bucket, key, "location").withFile(file4));
        s3.putObject(request1);
    }

    void putObject_requestPojoWithInputStream(String bucket, String key) {
        InputStream inputStream1 = new ByteArrayInputStream(("HelloWorld").getBytes());
        InputStream inputStream2 = new ByteArrayInputStream(("HolaWorld").getBytes());

        PutObjectRequest request1 = new PutObjectRequest(bucket, key, "location");
        request1.setInputStream(inputStream1);
        s3.putObject(request1);

        s3.putObject(new PutObjectRequest(bucket, key, "location").withInputStream(inputStream2));
    }

    void putObject_requestPojoWithoutPayload(String bucket, String key) {
        PutObjectRequest request = new PutObjectRequest(bucket, key, "location");
        s3.putObject(request);
    }


    void putObjectSetters() {
        PutObjectRequest putObjectRequest =
            new PutObjectRequest("bucket", "key", "location")
            .withBucketName("bucketName")
                .withCannedAcl(CannedAccessControlList.AwsExecRead);
    }

    void putObjectRequesterPaysSetter() {
        PutObjectRequest requestWithTrue = new PutObjectRequest("bucket", "key", "location").withRequesterPays(true);

        PutObjectRequest requestWithFalse = new PutObjectRequest("bucket", "key", "location").withRequesterPays(false);
    }
}