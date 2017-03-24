package com.guoxiaoxing.widget.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.guoxiaoxing.widget.dialog.entity.ListDialogEntity;
import com.guoxiaoxing.widget.dialog.listener.OnButtonClickListener;
import com.guoxiaoxing.widget.dialog.widget.SCDialog;
import com.guoxiaoxing.widget.dialog.widget.SCListDialog;
import com.guoxiaoxing.widget.dialog.widget.SCLoadingDialog;
import com.guoxiaoxing.widget.dialog.widget.SCSheetDialog;
import com.guoxiaoxing.widget.dialog.widget.SCSheetListDialog;
import com.guoxiaoxing.widget.dialog.widget.picker.SCDatePicker;
import com.guoxiaoxing.widget.dialog.widget.picker.SCOptionPicker;
import com.guoxiaoxing.widget.dialog.widget.picker.model.IPickerModel;
import com.guoxiaoxing.widget.dialog.widget.picker.model.PickerModel;
import com.guoxiaoxing.widget.snackbar.SCSnackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout mLlRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLlRoot = (LinearLayout) findViewById(R.id.activity_main);
        findViewById(R.id.base_show_dialog_loading).setOnClickListener(this);
        findViewById(R.id.base_show_dialog_loading_custome).setOnClickListener(this);
        findViewById(R.id.base_show_dialog_center_two).setOnClickListener(this);
        findViewById(R.id.base_show_dialog_center_one).setOnClickListener(this);
        findViewById(R.id.base_show_dialog_sheet).setOnClickListener(this);
        findViewById(R.id.base_show_dialog_sheet_list).setOnClickListener(this);
        findViewById(R.id.base_show_dialog_list).setOnClickListener(this);
        findViewById(R.id.base_show_dialog_option_picker).setOnClickListener(this);
        findViewById(R.id.base_show_dialog_date_picker_ymd).setOnClickListener(this);
        findViewById(R.id.base_show_dialog_date_picker_ym).setOnClickListener(this);
        findViewById(R.id.base_show_dialog_date_picker_y).setOnClickListener(this);
        findViewById(R.id.toast_btn_success).setOnClickListener(this);
        findViewById(R.id.toast_btn_default).setOnClickListener(this);
        findViewById(R.id.snackbar_yellow).setOnClickListener(this);
        findViewById(R.id.snackbar_yellow_with_arrow).setOnClickListener(this);
        findViewById(R.id.snackbar_yellow_action).setOnClickListener(this);
        findViewById(R.id.snackbar_black).setOnClickListener(this);
        findViewById(R.id.toast_msg).setOnClickListener(this);
        findViewById(R.id.multi_toast_msg).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.base_show_dialog_loading:
                new SCLoadingDialog(MainActivity.this).show();
                break;
            case R.id.base_show_dialog_loading_custome:
                new SCLoadingDialog(MainActivity.this, "自定义文案...", SCLoadingDialog.LOADING_TYPE_FENGCHE).show();
                break;
            case R.id.base_show_dialog_center_two:
                final SCDialog SCDialog = new SCDialog(MainActivity.this);
                SCDialog.withTitle("大风车")
                        .withContent("1.自动获取二手车之家平台线索，抢先赢得商机\n" +
                                "2.美图新增节日模板，分享更炫酷")//
                        .withContentGravity(Gravity.LEFT)
                        .withLeftButton("取消", new OnButtonClickListener() {
                            @Override
                            public void onButtonClick() {
                                SCDialog.dismiss();
                            }
                        })
                        .withRightButton("确定", new OnButtonClickListener() {
                            @Override
                            public void onButtonClick() {
                                SCDialog.dismiss();
                            }
                        }).show();
                break;
            case R.id.base_show_dialog_center_one:
                final SCDialog SCDialog1 = new SCDialog(MainActivity.this);
                SCDialog1.withTitle("大风车")
                        .withContent("自动获取二手车之家平台线索，抢先赢得商机")
                        .withCenterButton("我知道了").show();
                break;
            case R.id.base_show_dialog_sheet:
                final SCSheetDialog SCSheetDialog = new SCSheetDialog(MainActivity.this);
                SCSheetDialog.withContent("要删除这张图片吗?")
                        .withButtomButton("取消", new OnButtonClickListener() {
                            @Override
                            public void onButtonClick() {
                                SCSheetDialog.dismiss();
                            }
                        })
                        .withTopButton("删除", new OnButtonClickListener() {
                            @Override
                            public void onButtonClick() {
                                SCSheetDialog.dismiss();
                            }
                        }).show();
                break;
            case R.id.base_show_dialog_sheet_list:
                final SCSheetListDialog SCSheetListDialog = new SCSheetListDialog(MainActivity.this);
                SCSheetListDialog.withContent("您可以对文章进行以下操作")
                        //actionCode & actionName
                        .withAction("OPEN", "打开")
                        .withAction("OPEN_NEW", "在新标签页中打开")
                        .withAction("READ", "加入阅读列表")
                        .withAction("IMAGE", "存储图像")
                        .withAction("COPY", "拷贝")
                        .withActionClickListener(new SCSheetListDialog.ActionClickListener() {
                            @Override
                            public void actionClick(String actionCode, String actionName) {
                                Toast.makeText(MainActivity.this, "你点击了：actionCode = " + actionCode + "，actionName = " + actionName, Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
                break;
            case R.id.base_show_dialog_list:
                List<ListDialogEntity> list = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    ListDialogEntity entity = new ListDialogEntity();
                    entity.setCode(String.valueOf(i));
                    entity.setName("列表选项 " + i);
                    list.add(entity);
                }
                final SCListDialog SCListDialog = new SCListDialog(MainActivity.this);
                SCListDialog.withTitle("请选择")//
                        .withData(list)
                        .withOnCheckedListener(new SCListDialog.OnCheckedListener() {
                            @Override
                            public void onChecked(String code, String name) {
                                Toast.makeText(MainActivity.this, "你选中了：code = " + code + "，name = " + name, Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
                break;
            case R.id.base_show_dialog_option_picker:
                List<IPickerModel> data = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    IPickerModel entity = new PickerModel();
                    entity.setCode(String.valueOf(i));
                    entity.setName("列表选项 " + i);
                    data.add(entity);
                }
                new SCOptionPicker(MainActivity.this)
                        .withData(data)//必选以PickerModel（或者实现了IPickerModel的类）为Bean类的数据源列表
                        .withPickedOption(new PickerModel("2", "列表选项2"))//可选，选择项之间的比较以code为标准，如果code相等则相等
                        .withOptionPickedListener(new SCOptionPicker.OnOptionPickedListener() {//必选
                            @Override
                            public void onOptionPicked(String code, String name) {
                                Toast.makeText(MainActivity.this, "你选中了：code = " + code + "，name = " + name, Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
                break;
            case R.id.base_show_dialog_date_picker_ymd:
                new SCDatePicker(this)
                        .withType(SCDatePicker.DATE_FORMAT_YMD)//可选，有yyyy/MM/dd、yyyy/MM与yyyy两种日期模式，默认yyyy/MM/dd
                        .withPickedDate("2016/03/04")//可选，已选择日期，默认当前日期，可以传yyyy/MM/dd、yyyy/MM、yyyy和时间戳
                        .withPickedRange("2010/01/02", "2020/02/03")//可选，有三种方式：1 只有下限（上限传""） 2 只有上限（下限传""） 3 同时有上下限
                        .withDatePickedListener(new SCDatePicker.OnDatePickedListener() {//必选
                            @Override
                            public void onDatePickFailed() {
                                Toast.makeText(MainActivity.this, "请输入指定范围内的日期", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onDatePicked(long timestamp, String date) {//timestamp－时间戳，date－格式化后的日期（2018/2/3）
                                Toast.makeText(MainActivity.this, "你选中了：时间戳->" + timestamp + "  日期->" + date, Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
                break;
            case R.id.base_show_dialog_date_picker_ym:
                new SCDatePicker(this)
                        .withType(SCDatePicker.DATE_FORMAT_YM)//可选，有yyyy/MM/dd、yyyy/MM与yyyy两种日期模式，默认yyyy/MM/dd
                        .withPickedDate("2016/03")//可选，已选择日期，默认当前日期，可以传yyyy/MM/dd、yyyy/MM、yyyy和时间戳
                        .withPickedRange("2010/01", "2020/02")//可选，有三种方式：1 只有下限（上限传""） 2 只有上限（下限传""） 3 同时有上下限
                        .withDatePickedListener(new SCDatePicker.OnDatePickedListener() {//必选
                            @Override
                            public void onDatePickFailed() {
                                Toast.makeText(MainActivity.this, "请输入指定范围内的日期", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onDatePicked(long timestamp, String date) {//timestamp－时间戳，date－格式化后的日期（2018/2）
                                Toast.makeText(MainActivity.this, "你选中了：时间戳->" + timestamp + "  日期->" + date, Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
                break;
            case R.id.base_show_dialog_date_picker_y:
                new SCDatePicker(this)
                        .withType(SCDatePicker.DATE_FORMAT_Y)//可选，有yyyy/MM/dd、yyyy/MM与yyyy两种日期模式，默认yyyy/MM/dd
                        .withPickedDate("2016")//可选，已选择日期，默认当前日期，可以传yyyy/MM/dd、yyyy/MM、yyyy和时间戳
                        .withPickedRange("2010", "2020")//可选，有三种方式：1 只有下限（上限传""） 2 只有上限（下限传""） 3 同时有上下限
                        .withDatePickedListener(new SCDatePicker.OnDatePickedListener() {//必选
                            @Override
                            public void onDatePickFailed() {
                                Toast.makeText(MainActivity.this, "请输入指定范围内的日期", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onDatePicked(long timestamp, String date) {//timestamp－时间戳，date－格式化后的日期（2018）
                                Toast.makeText(MainActivity.this, "你选中了：时间戳->" + timestamp + "  日期->" + date, Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
                break;
            case R.id.toast_btn_success:

                break;
            case R.id.toast_btn_default:

                break;
            case R.id.snackbar_yellow:
                SCSnackbar.snack(mLlRoot, "将于三天后进行回访").show();
                break;
            case R.id.snackbar_yellow_with_arrow:
                SCSnackbar.snack(mLlRoot, "将于三天后进行回访", SCSnackbar.FCSNACKBAR_STYLE_YELLOW_WITH_ARROW)
                        .setContainerClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        }).show();
                break;
            case R.id.snackbar_yellow_action:
                SCSnackbar.snack(mLlRoot, "将于三天后进行回访")
                        .setAction("是的", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        }).show();
                break;
            case R.id.snackbar_black:
                SCSnackbar.snack(mLlRoot, "共5条筛选结果", SCSnackbar.FCSNACKBAR_STYLE_BLACK).show();
                break;
            default:
                break;
        }
    }
}
