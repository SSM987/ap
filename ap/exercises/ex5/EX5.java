package ap.exercises.ex5;

import java.util.ArrayList;
import java.util.List;

public class EX5 {
        private List<String> item;
        private List<Integer> count;

        public EX5() {
            this.item = new ArrayList<>();
            this.count = new ArrayList<>();
        }

        public void add(String item) {
            int index = this.item.indexOf(item);
            if (index != -1) {
                count.set(index, count.get(index) + 1);
            } else {
                this.item.add(item);
                count.add(1);
            }
        }

        public List<String> getTop(int k) {
            List<String> result = new ArrayList<>();
            List<Integer> counts = new ArrayList<>(count);
            List<String> items = new ArrayList<>(item);

            for (int i = 0; i < k && !counts.isEmpty(); i++) {
                int maxIndex = 0;
                for (int j = 1; j < counts.size(); j++) {
                    if (counts.get(j) > counts.get(maxIndex)) {
                        maxIndex = j;
                    }
                }
                result.add(items.get(maxIndex));
                items.remove(maxIndex);
                counts.remove(maxIndex);
            }

            return result;
        }
    }
