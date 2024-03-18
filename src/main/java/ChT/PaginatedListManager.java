package ChT;

import java.util.ArrayList;
import java.util.List;

public class PaginatedListManager<T> {
    private List<T> items;
    private int pageSize;
    private int currentPage;

    public PaginatedListManager(int pageSize) {
        this.items = new ArrayList<>();
        this.pageSize = pageSize;
        this.currentPage = 0;
    }

    public void addItem(T item) {
        items.add(item);
    }

    public void setPage(int page) {
        currentPage = Math.min(Math.max(page, 0), getTotalPages() - 1);
    }

    public void nextPage() {
        if (currentPage < getTotalPages() - 1) {
            currentPage++;
        }
    }

    public void prevPage() {
        if (currentPage > 0) {
            currentPage--;
        }
    }

    public void firstPage() {
        currentPage = 0;
    }

    public void lastPage() {
        currentPage = getTotalPages() - 1;
    }

    public int getTotalPages() {
        return (int) Math.ceil((double) items.size() / pageSize);
    }

    public List<T> getCurrentPageItems() {
        int fromIndex = currentPage * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, items.size());

        if (fromIndex < 0 || fromIndex >= items.size() || fromIndex >= toIndex) {
            return new ArrayList<>();
        }

        return items.subList(fromIndex, toIndex);
    }

    public int getCurrentPage() {
        return currentPage;
    }
}
