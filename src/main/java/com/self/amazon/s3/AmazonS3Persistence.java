package com.self.amazon.s3;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

/**
 * Componente resposável pela comunicação com o AmazonS3
 * 
 * @author rafael
 *
 */
@Component
public class AmazonS3Persistence {

	@Value("${amazon.s3.accesstoken}")
	private String accessKey;

	@Value("${amazon.s3.secret}")
	private String secretKey;

	@Value("${amazon.s3.bucket}")
	private String bucket;

	private static final String SUFFIX = "/";

	BasicAWSCredentials credentials;
	AmazonS3 s3;

	public void login() {
		credentials = new BasicAWSCredentials(accessKey, secretKey);
		s3 = AmazonS3Client.builder().withRegion(Regions.US_EAST_2)
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
	}

	/**
	 * Lista os buckets disponíveis no S3
	 * 
	 * @return Lista com os buckets
	 */
	public List<Bucket> listBucktes() {

		return s3.listBuckets();
	}

	/**
	 * Envia um arquivo para o S3, utilizando um endereço específico
	 * 
	 * @param file     Arquivo para enviar em byte[]
	 * @param filename Caminho do arquivo mais o nome deste. (ex.
	 *                 pastateste/arquivo.pdf). O caminho será criado dentro do
	 *                 bucket que foi carregado em "amazon.s3.bucket" no arquivo de
	 *                 propriedades
	 */
	public void uploadFileToBucket(byte[] file, String filename) {

		try {
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(file.length);
			InputStream inputStream = new ByteArrayInputStream(file);
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, filename, inputStream,
					metadata);

			s3.putObject(putObjectRequest);

		} catch (AmazonS3Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cria uma pasta no S3
	 * 
	 * @param folderName Caminho da pasta (ex. pasta1/pasta2).O caminho será criado
	 *                   dentro do bucket que foi carregado em "amazon.s3.bucket" no
	 *                   arquivo de propriedades
	 */
	public void createFolderInsideBucket(String folderName) {

		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);

		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);

		PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, folderName + SUFFIX,
				emptyContent, metadata);

		s3.putObject(putObjectRequest);

	}

	/**
	 * Retorna o endereço no S3 do arquivo
	 * 
	 * @param filePath
	 * @return
	 */
	public String getFileUrlPath(String filePath) {

		URL url = s3.getUrl(bucket, filePath);

		return url.toString();
	}
}
