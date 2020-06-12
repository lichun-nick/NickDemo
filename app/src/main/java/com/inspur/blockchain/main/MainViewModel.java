package com.inspur.blockchain.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inspur.blockchain.UrlConfig;
import com.inspur.blockchain.model.MainInfoBean;

/**
 * @author lichun
 */
public class MainViewModel extends ViewModel {

    private MainRepository mainRepository;

    public MainViewModel(){
        this.mainRepository = new MainRepository();
    }

    public LiveData<MainInfoBean> getData(){
        return mainRepository.requestData();
    }
}
