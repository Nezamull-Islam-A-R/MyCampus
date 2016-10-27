package Modules;

/**
 * Created by Nezamul Islam A R on 8/5/2016.
 */
import java.util.List;
public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);
}
