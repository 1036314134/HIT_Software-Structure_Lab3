package P1;

import java.util.*;


public class PlanningEntryIterator<E extends Comparable<E>, R extends Resource> implements Iterator<E> {
	private List<E> ObjectList;
	private int index;
	private int size;

	public PlanningEntryIterator(Map<PlanningEntry<R>, List<E>> map) {
		index = 0;
		size = 0;
		for (Map.Entry<PlanningEntry<R>, List<E>> entry: map.entrySet()) {
			size += entry.getValue().size();
		}
		ObjectList = map.keySet().stream().sorted().map(map::get).reduce(new ArrayList<>(), (result, element) -> {
			Collections.sort(element);
			result.addAll(element);
			return result;
		});
	}

	@Override
	public boolean hasNext() {
		return index < size;
	}

	@Override
	public E next() {
		return ObjectList.get(index++);
	}

}
