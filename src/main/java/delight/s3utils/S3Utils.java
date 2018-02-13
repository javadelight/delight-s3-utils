package delight.s3utils;

import java.util.Iterator;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteBucketRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class S3Utils {
	
	/**
	 * <p>Deletes a bucket and 
	 * @param region
	 * @param bucketName
	 * @param credentials
	 */
	public static void deleteBucket(final String region, String bucketName,
			final AWSCredentials credentials) {
		final AmazonS3 s3client = new AmazonS3Client(credentials);
		s3client.setRegion(Region.getRegion(Regions.fromName(region)));
			
		// delete all objects
		ObjectListing object_listing = s3client.listObjects(bucketName);
		while (true) {
		    for (Iterator<?> iterator =
		            object_listing.getObjectSummaries().iterator();
		            iterator.hasNext();) {
		        S3ObjectSummary summary = (S3ObjectSummary)iterator.next();
		        s3client.deleteObject(bucketName, summary.getKey());
		    }
	
		    // more object_listing to retrieve?
		    if (object_listing.isTruncated()) {
		        object_listing = s3client.listNextBatchOfObjects(object_listing);
		    } else {
		        break;
		    }
		};
		
		// delete bucket
		s3client.deleteBucket(new DeleteBucketRequest(bucketName));
	}

}
