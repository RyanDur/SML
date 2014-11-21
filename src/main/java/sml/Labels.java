package sml;

import java.util.ArrayList;

import static java.util.stream.Collectors.joining;

//An instance contains a list of Strings, called "labels",
//in the order in which they were added to the list. 

public class Labels {

	private ArrayList<String> labels;

	{
		labels = new ArrayList<>();
	}

	// Add label lab to this list and return its number in the list
	// (the first one added is number 0)
	// Precondition: the list has at most 49 entries
	public int addLabel(String lab) {
		labels.add(lab);
		return labels.size() - 1;
	}

	// = the number of label lab in the list
	// (= -1 if lab is not in the list)
	public int indexOf(String lab) {
		return labels.indexOf(lab);
	}

	// representation of this instance, "(label 0, label 1, ..., label (n-1))"
	@Override
	public String toString() {
		return "(" + labels.stream().map(String::toString).collect(joining(", ")) + ")";
	}

	// Set the number of elements in the list to 0

	public void reset() {
		labels.clear();
	}
}