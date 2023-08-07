package com.bluewhaleyt.materialfileicon.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bluewhaleyt.materialfileicon.adapter.DrawableResListAdapter;
import com.bluewhaleyt.materialfileicon.databinding.ActivityIconListBinding;
import com.bluewhaleyt.materialfileicon.model.DrawableResModel;

import java.util.ArrayList;
import java.util.List;

public class IconListActivity extends AppCompatActivity {

    private ActivityIconListBinding binding;

    private List<DrawableResModel> listDrawableRes = new ArrayList<>();
    private DrawableResModel modelDrawableRes;
    private DrawableResListAdapter adapterDrawableRes = new DrawableResListAdapter((ArrayList<DrawableResModel>) listDrawableRes);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIconListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvIconList.setLayoutManager(linearLayoutManager);
//        showDrawableRes();
    }

//    private void showDrawableRes() {
//        clearAllLists();
//        binding.rvIconList.setAdapter(adapterDrawableRes);
//        try {
//            var x = SystemResourceUtil.getDrawableResources(this);
//            for (int i = 0; i < x.size(); i++) {
//                modelDrawableRes = new DrawableResModel();
//                modelDrawableRes.setDrawableRes(x.get(i));
//                var temp = SystemResourceUtil.getParsedXMLResource(this, modelDrawableRes.getDrawableRes(), "drawable");
//                modelDrawableRes.setDrawablePreview(temp);
//
//                if (modelDrawableRes.getDrawableRes().contains("ic_material")) {
//                    listDrawableRes.add(modelDrawableRes);
//                }
//
//                Comparator<DrawableResModel> comparator = (o1, o2) -> {
//                    // Check if resource names contain "ic_material_folder_"
//                    boolean o1IsFolder = o1.getDrawableRes().startsWith("ic_material_folder");
//                    boolean o2IsFolder = o2.getDrawableRes().startsWith("ic_material_folder");
//
//                    if (o1IsFolder && !o2IsFolder) {
//                        return -1; // o1 comes first
//                    } else if (!o1IsFolder && o2IsFolder) {
//                        return 1; // o2 comes first
//                    } else if (o1IsFolder && o2IsFolder) {
//                        // Both resources are "ic_material_folder" or "ic_material_folder_xxx"
//                        if (o1.getDrawableRes().equals("ic_material_folder")) {
//                            return -1; // "ic_material_folder" comes first
//                        } else if (o2.getDrawableRes().equals("ic_material_folder")) {
//                            return 1; // "ic_material_folder" comes first
//                        } else {
//                            return o1.getDrawableRes().compareTo(o2.getDrawableRes()); // Sort alphabetically
//                        }
//                    } else {
//                        return o1.getDrawableRes().compareTo(o2.getDrawableRes()); // Sort alphabetically
//                    }
//                };
//
//                // Set the count for each item
//                int count = 1;
//                for (DrawableResModel item : listDrawableRes) {
//                    item.setCount(count++);
//                }
//
//                // Sort the list using the custom comparator
//                Collections.sort(listDrawableRes, comparator);
//
//                adapterDrawableRes.notifyDataSetChanged();
//
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    private void clearAllLists() {
        listDrawableRes.clear();
    }
}