# delight-s3-utils

Utilities for working with the S3 API in Java.

## Usage

Empty and then delete a bucket:

```java

AWSCredentials credentials = new AWSCredentials() {
	@Override
	public getAWSAccessKeyId() {
		return "awsAccessKey";
	}
	
	@Override
	public getAWSSecretKey() {
		return "awsSecretKey";
	}
	
};

S3Utils.deleteBucket(region, bucketName, awsCredentials);
```