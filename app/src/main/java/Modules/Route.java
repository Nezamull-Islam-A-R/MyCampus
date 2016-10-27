package Modules;

/**
 * Created by Nezamul Islam A R on 8/5/2016.
 */

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.nearby.messages.Distance;

import java.util.List;

import javax.xml.datatype.Duration;

/**
 * Created by Mai Thanh Hiep on 4/3/2016.
 */
public class Route {
    public Modules.Distance distance;
    public Modules.Duration duration;
    public String endAddress;
    public LatLng endLocation;
    public String startAddress;
    public LatLng startLocation;

    public List<LatLng> points;
}
