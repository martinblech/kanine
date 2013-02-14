package kanine.benchmark;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;

import com.google.caliper.Param;
import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;

public class ArrayVsMMap extends SimpleBenchmark {

	@Param("100") int vectorSize;
	@Param({"10000", "100000", "1000000"}) int nVectors;
	@Param ByteOrderType byteOrder;

	public static enum ByteOrderType {
		LITTLE_ENDIAN {
			@Override
			ByteOrder getByteOrder() {
				return ByteOrder.LITTLE_ENDIAN;
			}
		},
		BIG_ENDIAN {
			@Override
			ByteOrder getByteOrder() {
				return ByteOrder.BIG_ENDIAN;
			}
		};
		abstract ByteOrder getByteOrder();
	}

	private String dataFilePath;
	private RandomAccessFile file;
	private FileChannel channel;
	private MappedByteBuffer buffer;
	private FloatBuffer floatBuffer;
	private float[] data;
	private FloatBuffer wrappedData;

	@Override
	protected void setUp() throws Exception {
		data = new float[vectorSize*nVectors];
		final Random random = new Random(0);
		for (int i = 0; i < data.length; i++) {
			data[i] = random.nextFloat();
		}
		wrappedData = FloatBuffer.wrap(data);
		dataFilePath = File.createTempFile("mmap", ".tmp").getAbsolutePath();
		file = new RandomAccessFile(dataFilePath, "rw");
		channel = file.getChannel();
		buffer = channel.map(
				FileChannel.MapMode.READ_WRITE, 0, 4*data.length);
		buffer.limit(4*data.length);
		buffer.order(byteOrder.getByteOrder());
		floatBuffer = buffer.asFloatBuffer();
		floatBuffer.put(data);
		buffer.force();
		buffer.load();
		buffer.limit(4*data.length);
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

	public float timeArray_FloatBuffer_Wrap_Get(int reps) {
		float sum = 0;
		for (int rep = 0; rep < reps; rep++) {
			for (int i = 0; i < nVectors; i++) {
				sum /= calc(wrappedData, i * vectorSize, vectorSize);
			}
		}
		return sum;
	}

	public float timeMMap_FloatBuffer_Get(int reps) {
		float sum = 0;
		for (int rep = 0; rep < reps; rep++) {
			for (int i = 0; i < nVectors; i++) {
				sum /= calc(floatBuffer, i * vectorSize, vectorSize);
			}
		}
		return sum;
	}

	public float timeMMap_FloatBuffer_BulkGet(int reps) {
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

	public float timeMMap_GetFloat(int reps) {
		float sum = 0;
		for (int rep = 0; rep < reps; rep++) {
			for (int i = 0; i < nVectors; i++) {
				sum /= calc(buffer, i * vectorSize, vectorSize);
			}
		}
		return sum;
	}

	private final static float calc(float[] data, int offset, int length) {
		float sum = 0;
		for (int i = offset; i < offset + length; i++) {
			sum += data[i];
		}
		return sum / length;
	}

	private final static float calc(MappedByteBuffer data, int offset, int length) {
		float sum = 0;
		for (int i = offset; i < offset + length; i++) {
			sum += data.getFloat(i * 4);
		}
		return sum / length;
	}

	private final static float calc(FloatBuffer data, int offset, int length) {
		float sum = 0;
		for (int i = offset; i < offset + length; i++) {
			sum += data.get(i);
		}
		return sum / length;
	}

	public static void main(String[] args) {
		Runner.main(ArrayVsMMap.class, args);
	}
}
