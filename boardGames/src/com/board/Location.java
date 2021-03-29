package com.board;

public class Location {
	private final File file;
	private final Integer rank;
	

	public Location(File file, Integer rank) {
		this.file = file;
		this.rank = rank;
	}

	public File getFile() {
		return file;
	}

	public Integer getRank() {
		return rank;
	}

	@Override
	public String toString() {
		return "Location [file=" + file + ", rank=" + rank + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((file == null) ? 0 : file.hashCode());
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (file != other.file)
			return false;
		if (rank == null) {
			if (other.rank != null)
				return false;
		} else if (!rank.equals(other.rank))
			return false;
		return true;
	}
}
