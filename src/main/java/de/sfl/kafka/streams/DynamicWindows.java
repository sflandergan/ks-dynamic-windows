package de.sfl.kafka.streams;

import java.time.Duration;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.apache.kafka.streams.kstream.internals.WindowingDefaults;

import com.google.common.collect.Range;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class DynamicWindows implements Iterable<Duration> {

	private static final Duration DEFAULT_MINIMUM = Duration.ofSeconds(1L);

	private static final Duration DEFAULT_ADVANCE = Duration.ofSeconds(1L);

	private static final Duration DEFAULT_RETENTION = Duration
			.ofMillis(WindowingDefaults.DEFAULT_RETENTION_MS);

	private final Duration minimum;

	private final Duration maximum;

	private final Duration advanceBy;

	private final Duration gracePeriod;

	private final Duration retentionTime;

	public static DynamicWindows between(Duration minimum, Duration maximum) {
		return new DynamicWindows(minimum, maximum, DEFAULT_ADVANCE, null, DEFAULT_RETENTION);
	}

	public static DynamicWindows maximum(Duration maximum) {
		return new DynamicWindows(DEFAULT_MINIMUM, maximum, DEFAULT_ADVANCE, null,
				DEFAULT_RETENTION);
	}
	
	public DynamicWindows advanceBy(Duration advanceBy) {
		return new DynamicWindows(minimum, maximum, advanceBy, gracePeriod, retentionTime);
	}
	
	public DynamicWindows gracePeriod(Duration gracePeriod) {
		return new DynamicWindows(minimum, maximum, advanceBy, gracePeriod, retentionTime);
	}
	
	public DynamicWindows retentionTime(Duration retentionTime) {
		return new DynamicWindows(minimum, maximum, advanceBy, gracePeriod, retentionTime);
	}

	@Override
	public Iterator<Duration> iterator() {
		return new DurationAdvanceIterator();
	}

	public boolean isInRange(Duration duration) {
		var range = Range.closed(minimum, maximum);
		if (range.contains(duration)) {
			return duration.minus(minimum).toMillis() % advanceBy.toMillis() == 0;
		}

		return false;
	}

	private class DurationAdvanceIterator implements Iterator<Duration> {

		private Duration currentElement = minimum;

		@Override
		public boolean hasNext() {
			return currentElement.plus(advanceBy).compareTo(maximum) <= 0;
		}

		@Override
		public Duration next() {
			currentElement = currentElement.plus(advanceBy);
			if (currentElement.compareTo(maximum) > 0) {
				throw new NoSuchElementException();
			}
			return currentElement;
		}

	}
}
