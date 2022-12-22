package queimando.iftm.firebase;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;

@Service
public class FirebaseCloudStorage {

  private final String storageBucket;
  private final FileInputStream serviceAccount;
  private final Storage storage;

  public FirebaseCloudStorage() throws IOException {
    this.storageBucket = "queimando-d707b.appspot.com";
    this.serviceAccount = new FileInputStream(System.getenv("GOOGLE_APPLICATION_CREDENTIALS"));
    FirebaseOptions options = FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).setStorageBucket(storageBucket).build();
    FirebaseApp.initializeApp(options);
    Bucket bucket = StorageClient.getInstance().bucket();
    this.storage = bucket.getStorage();
  }

  public void upload(InputStream arquivoUploaded, String arquivoStoraged) throws Exception {
    System.out.println("inicio");
    BlobId blobId = BlobId.of(storageBucket, arquivoStoraged);
    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
    Storage.BlobTargetOption precondition;
    if (storage.get(storageBucket, arquivoStoraged) == null) {
      precondition = Storage.BlobTargetOption.doesNotExist();
    } else {
      precondition = Storage.BlobTargetOption.generationMatch();
    }
    Blob blob = storage.create(blobInfo, arquivoUploaded.readAllBytes(), precondition);
    storage.createAcl(blobId, Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));

    System.out.println(blob.getSelfLink());
  }
}