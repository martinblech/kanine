package kanine.benchmark;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.FloatBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;

import com.google.caliper.Param;
import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;

public class ArrayVsMMap extends SimpleBenchmark {
	
	@Param("100") int vectorSize;
	@Param({"10000", "100000"}) int nVectors;
	
	private String dataFilePath;
	private RandomAccessFile file;
	private FileChannel channel;
	private MappedByteBuffer buffer;
	private FloatBuffer floatBuffer;
	private float[] data;
	
	@Override
	protected void setUp() throws Exception {
		data = new float[vectorSize*nVectors];
		final Random random = new Random(0);
		for (int i = 0; i < data.length; i++) {
			data[i] = random.nextFloat();
		}
		dataFilePath = File.createTempFile("mmap", ".tmp").getAbsolutePath();
		file = new RandomAccessFile(dataFilePath, "rw");
		channel = file.getChannel();
		buffer = channel.map(
				FileChannel.MapMode.READ_WRITE, 0, 4*data.length);
		buffer.limit(4*data.length);
		floatBuffer = buffer.asFloatBuffer();
		floatBuffer.put(data);
		buffer.force();
		buffer.load();
		buffer.flip();
	}
	
	@Override
	protected void tearDown() throws Exception {
		channel.close();
		file.close();
		new File(dataFilePath).delete();
	}
	
	public float timeArray(int reps) {
		float sum = 0;
		for (int rep = 0; rep < reps; rep++) {
			for (int i = 0; i < nVectors; i++) {
				sum /= calc(data, i * vectorSize, vectorSize);
			}
		}
		return sum;
	}

	public float timeMMap(int reps) {
		float sum = 0;
		for (int rep = 0; rep < reps; rep++) {
			floatBuffer.rewind();
			float[] vector = new float[vectorSize];
			for (int i = 0; i < nVectors; i++) {
				floatBuffer.get(vector);
				sum /= calc(vector, 0, vector.length);
			}
		}
		return sum;
	}
	
	protected final static float calc(float[] data, int offset, int length) {
		float sum = 0;
		for (int i = offset; i < offset + length; i++) {
			sum += data[i];
		}
		return sum / length;
	}

	public static void main(String[] args) {
		Runner.main(ArrayVsMMap.class, args);
	}
}
