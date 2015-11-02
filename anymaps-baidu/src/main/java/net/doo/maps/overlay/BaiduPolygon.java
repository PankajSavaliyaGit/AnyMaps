/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.overlay;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.Stroke;

import net.doo.maps.model.LatLng;
import net.doo.maps.model.Polygon;

import java.util.List;

public class BaiduPolygon implements Polygon {

	private com.baidu.mapapi.map.Polygon polygon;
	private BaiduMap map;

	public BaiduPolygon(com.baidu.mapapi.map.Polygon polygon) {
		this.polygon = polygon;
	}

	public BaiduPolygon(BaiduMap map) {
		this.map = map;
	}

	@Override
	public void setHoles(List<List<LatLng>> holes) {
		for (List<LatLng> hole : holes) {
			com.baidu.mapapi.map.Overlay polygon = map.addOverlay(new PolygonOptions()
					.fillColor(0x4499FF99)
					.points(Converter.convert(hole))
					.stroke(new Stroke(8, 0xa600aff8)));
		}
	}

	@Override
	public List<LatLng> getPoints() {
		return OutConverter.convert(polygon.getPoints());
	}

	@Override
	public void setVisible(final boolean visible) {
		if (polygon.isVisible() != visible) {
			polygon.setVisible(visible);
		}
	}

	@Override
	public void remove() {
		polygon.remove();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof BaiduPolygon)) return false;

		BaiduPolygon that = (BaiduPolygon) o;

		return polygon.equals(that.polygon);
	}

	@Override
	public int hashCode() {
		return polygon.hashCode();
	}
}
