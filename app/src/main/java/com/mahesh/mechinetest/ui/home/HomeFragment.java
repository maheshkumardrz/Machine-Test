package com.mahesh.mechinetest.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mahesh.mechinetest.R;
import com.mahesh.mechinetest.databinding.FragmentHomeBinding;
import com.mahesh.mechinetest.ui.DataModel;
import com.mahesh.mechinetest.ui.adapter.BirthDaysAdapter;
import com.mahesh.mechinetest.ui.adapter.MyHouseHoldersAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements MyHouseHoldersAdapter.ItemListener,BirthDaysAdapter.ItemListener{

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    ArrayList<DataModel> arrayList,arrayList2;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        arrayList = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        arrayList.add(new DataModel("Rachel Thomas", R.drawable.rachel_thomas, "Wife"));
        arrayList.add(new DataModel("Aby Thomas", R.drawable.aby_thomas, "Sister"));
        arrayList.add(new DataModel("Aby Thomas", R.drawable.brother, "Brother"));

        arrayList2.add(new DataModel("Rachel Thomas", R.drawable.rachel_thomas, "Feb 25 2021,Monday"));
        arrayList2.add(new DataModel("Aby Thomas", R.drawable.aby_thomas, "Feb 25 2021,Monday"));
        arrayList2.add(new DataModel("Aby Thomas", R.drawable.brother, "Feb 25 2021,Monday"));


        MyHouseHoldersAdapter myHouseHoldersAdapter = new MyHouseHoldersAdapter(getActivity(), arrayList, this);
        binding.myHouseHolds.setAdapter(myHouseHoldersAdapter);
        binding.myHouseHolds.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


        BirthDaysAdapter birthDaysAdapter = new BirthDaysAdapter(getActivity(), arrayList2, this);
        binding.birthdaysList.setAdapter(birthDaysAdapter);
        binding.birthdaysList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(DataModel item) {

    }
}