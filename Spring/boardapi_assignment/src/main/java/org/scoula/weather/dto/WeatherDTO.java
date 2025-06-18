package org.scoula.weather.dto;

import java.util.List;
import lombok.Data;

/*openweather api
○ 온도는섭씨로처리를사용하여서울의날씨를확인하세요.
* {
"coord": {
"lon": 126.9778,
"lat": 37.5683
},
"weather": [
{
"id": 800,
"main": "Clear",
"description": "맑음",
"icon": "01d"
}
],
"base": "stations",
"main": {
"temp": 29.76,
"feels_like": 30.01,
"temp_min": 29.76,
"temp_max": 30.78,
"pressure": 1011,
"humidity": 45,
"sea_level": 1011,
"grnd_level": 1001
},
"visibility": 10000,
"wind": {
"speed": 4.12,
"deg": 260
},
"clouds": {
"all": 0
},
"dt": 1750231471,
"sys": {
"type": 1,
"id": 8105,
"country": "KR",
"sunrise": 1750191032,
"sunset": 1750244149
},
"timezone": 32400,
"id": 1835848,
"name": "Seoul",
"cod": 200
}

해당날씨에대한아이콘을얻는url을작성하고, 확인
http://openweathermap.org/img/w/01d.png*/
@Data
public class WeatherDTO{
	private int visibility;
	private int timezone;
	private Main main;
	private Clouds clouds;
	private Sys sys;
	private int dt;
	private Coord coord;
	private List<WeatherItem> weather;
	private String name;
	private int cod;
	private int id;
	private String base;
	private Wind wind;
}