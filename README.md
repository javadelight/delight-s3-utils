# delight-s3-utils

Utilities for working with the S3 API in Java.

## Usage

Empty and then delete a bucket:

```java
S3Utils.deleteBucket(region, bucketName, awsCredentials);
```