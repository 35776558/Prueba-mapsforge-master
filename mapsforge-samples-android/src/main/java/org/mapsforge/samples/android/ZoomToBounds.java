/*
 * Copyright 2013-2014 Ludwig M Brinckmann
 * Copyright 2014 Christian Pesch
 * Copyright 2018 devemux86
 *
 * This program is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.mapsforge.samples.android;

import org.mapsforge.core.graphics.Color;
import org.mapsforge.core.graphics.Style;
import org.mapsforge.core.model.BoundingBox;
import org.mapsforge.core.model.Dimension;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.core.model.MapPosition;
import org.mapsforge.core.util.LatLongUtils;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.layer.Layers;
import org.mapsforge.map.layer.overlay.Polyline;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic map viewer with a few overlays added.
 */
public class ZoomToBounds extends OverlayMapViewer {

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            BoundingBox bb = new BoundingBox(latLong2.latitude,
                    latLong3.longitude, latLong3.latitude, latLong2.longitude);
            Dimension dimension = this.mapView.getModel().mapViewDimension.getDimension();
            this.mapView.getModel().mapViewPosition.setMapPosition(new MapPosition(
                    bb.getCenterPoint(),
                    LatLongUtils.zoomForBounds(
                            dimension,
                            bb,
                            this.mapView.getModel().displayModel.getTileSize())));
        }
    }

    @Override
    protected void addOverlayLayers(Layers layers) {
        Polyline polyline = new Polyline(Utils.createPaint(
                AndroidGraphicFactory.INSTANCE.createColor(Color.BLUE), 8,
                Style.STROKE), AndroidGraphicFactory.INSTANCE);
        List<LatLong> latLongs = new ArrayList<>();
        latLongs.add(latLong2);
        latLongs.add(latLong3);
        polyline.setPoints(latLongs);
        layers.add(polyline);
    }

}
