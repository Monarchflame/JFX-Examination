package com.desktop.util;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 通用工具方法
 *
 * @author qxt
 * @date 2020/12/7 14:50
 */
public class CommonUtil {
    /**
     * 格式化表格日期列
     *
     * @param tableColumn
     * @param <T>
     */
    public static <T> void formatDate(TableColumn<T, Date> tableColumn) {
        tableColumn.setCellFactory(column -> new TableCell<T, Date>() {
            private final SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");

            @Override
            protected void updateItem(Date item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    this.setText(format.format(item));
                }
            }
        });
    }
}
