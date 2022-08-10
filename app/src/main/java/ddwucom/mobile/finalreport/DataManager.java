package ddwucom.mobile.finalreport;

import java.util.ArrayList;

public class DataManager {

    ArrayList<MyData> myDataList;

    public DataManager() {
        myDataList = new ArrayList<MyData>();
        myDataList.add(new MyData(1, R.mipmap.ic_launcher, "제에목", "저어자", "출판사", 1000, 50));
        myDataList.add(new MyData(2, R.mipmap.ic_launcher, "제에목", "저어자", "출판사", 1000, 50));
    }

    public ArrayList<MyData> getMyDataList() {
        return myDataList;
    }

    public void addMyData(MyData data) {
        myDataList.add(data);
    }

    public void deleteMydata(int index) {
        myDataList.remove(index);
    }
}
