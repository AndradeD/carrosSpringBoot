package com.carros.domain.upload;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.carros.api.upload.UploadInput;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;

@Service
public class FirebaseStorageService {

	@PostConstruct
	private void init() throws IOException {
		if (FirebaseApp.getApps().isEmpty()) {
			InputStream in = FirebaseStorageService.class.getResourceAsStream("/serviceAccountKey.json");
			
			System.out.println(in);
			
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(in))
					.setStorageBucket("carrosspringboot.appspot.com")
					.setDatabaseUrl("https://carrosspringboot.firebaseio.com")
					.build();
			
			FirebaseApp.initializeApp(options);
		}
	}
	
	public String upload(UploadInput input) {
		
		Bucket bucket = StorageClient.getInstance().bucket();
		System.out.println(bucket);
		
		byte[] bytes = Base64.getDecoder().decode(input.getBase64());
		
		Blob blob = bucket.create(input.getFileName(), bytes, input.getMimeType());
		blob.createAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
		
		return String.format("https://storage.cloud.google.com/%s/%s", bucket.getName(), input.getFileName());
	}
}
