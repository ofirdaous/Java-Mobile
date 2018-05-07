package Abstracts;

import Entities.Settings;

public class BaseRepository {
	protected Settings settings;

	public BaseRepository(Settings settings){
		this.settings = settings;
	}
}
