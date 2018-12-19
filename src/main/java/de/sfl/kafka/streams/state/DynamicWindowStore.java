package de.sfl.kafka.streams.state;

import java.time.Duration;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.kstream.Windowed;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.StateStore;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.WindowStore;
import org.apache.kafka.streams.state.WindowStoreIterator;

import de.sfl.kafka.streams.DynamicWindows;
import lombok.RequiredArgsConstructor;

class DynamicWindowStore<K, V> implements WindowStore<K, V> {

	private final String name;

	private final DynamicWindows windows;

	private ProcessorContext context;

	private Map<Duration, WindowStore<K, V>> windowStores;

	public DynamicWindowStore(String name, DynamicWindows windows) {
		this.name = name;
		this.windows = windows;
	}

	Map<String, StoreBuilder<WindowStore<K, V>>> buildInternalStores() {
		return StreamSupport.stream(windows.spliterator(), false).collect(
				Collectors.toMap(this::buildInternalStoreName, this::createStoreBuilder));
	}

	private StoreBuilder<WindowStore<K, V>> createStoreBuilder(Duration window) {
		// TODO Auto-generated method stub
		return null;
	}

	private String buildInternalStoreName(Duration window) {
		return String.format("%s-%s", name, window);
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public void init(ProcessorContext context, StateStore root) {
		this.context = context;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean persistent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V fetch(K key, long time) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeyValueIterator<Windowed<K>, V> all() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put(K key, V value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void put(K key, V value, long windowStartTimestamp) {
		// TODO Auto-generated method stub

	}

	@Override
	public WindowStoreIterator<V> fetch(K key, long timeFrom, long timeTo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeyValueIterator<Windowed<K>, V> fetch(K from, K to, long timeFrom, long timeTo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeyValueIterator<Windowed<K>, V> fetchAll(long timeFrom, long timeTo) {
		// TODO Auto-generated method stub
		return null;
	}
}
