package com.blogspot.e_kanivets.moneytracker.activity;

import android.widget.EditText;

import com.blogspot.e_kanivets.moneytracker.R;
import com.blogspot.e_kanivets.moneytracker.activity.base.BaseBackActivity;
import com.blogspot.e_kanivets.moneytracker.controller.external.ImportController;
import com.blogspot.e_kanivets.moneytracker.entity.data.Record;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class ImportActivity extends BaseBackActivity {
    @Inject
    ImportController importController;

    @Bind(R.id.et_import_data)
    EditText etImportData;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_import;
    }

    @Override
    protected boolean initData() {
        boolean result = super.initData();
        getAppComponent().inject(ImportActivity.this);
        return result;
    }

    @OnClick(R.id.btn_import)
    public void importRecords() {
        String data = etImportData.getText().toString().trim();
        List<Record> recordList = importController.importRecordsFromCsv(data);
        showToast(getString(R.string.records_imported, recordList.size()));

        setResult(RESULT_OK);
        finish();
    }
}
