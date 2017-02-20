package watchable;

import java.awt.Color;

public interface Watchable {
	void update(Color[][] grid); //sends an update notification to all watchable classes
}
