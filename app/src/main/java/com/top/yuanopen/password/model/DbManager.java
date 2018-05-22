package com.top.yuanopen.password.model;

/**
 * Created by yuanopen on 2018/4/12/012.
 */

public interface DbManager {

    public   void OpenDb();
     public void QueryDate(Db_QueryListener listener);
     public   void WriteDate( Object object);
    public  void UpdateDate(Object object);

}
