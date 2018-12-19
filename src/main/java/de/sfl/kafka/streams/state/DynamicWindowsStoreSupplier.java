package de.sfl.kafka.streams.state;

import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.state.WindowBytesStoreSupplier;
import org.apache.kafka.streams.state.WindowStore;

public class DynamicWindowsStoreSupplier implements WindowBytesStoreSupplier {

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WindowStore<Bytes, byte[]> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String metricsScope() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int segments() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long segmentIntervalMs() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long windowSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean retainDuplicates() {
		return false;
	}

	@Override
	public long retentionPeriod() {
		// TODO Auto-generated method stub
		return 0;
	}

}
