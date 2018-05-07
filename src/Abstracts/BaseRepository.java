package Abstracts;

import java.text.SimpleDateFormat;

import Entities.Settings;

public class BaseRepository {
	//region Members
	protected Settings settings;
	protected SimpleDateFormat sourceFormat;
	//endregion

	//region Constructor
	public BaseRepository(Settings settings){
		this.settings = settings;
		this.sourceFormat = new SimpleDateFormat("dd-MM-yyyy");
	}
	//endregion
}
