package layer.survice;

import java.util.List;
import java.util.Map;

public interface orderService {
    String submitService(List<Map<String,Object>> cart);
}
