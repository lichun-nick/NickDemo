package com.inspur.blockchain.voucher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.inspur.blockchain.model.VoucherListItemBean;

import java.util.List;

/**
 * @author lichun
 */
class VoucherListCallback extends DiffUtil.Callback {

    private List<VoucherListItemBean> oldList;
    private List<VoucherListItemBean> newList;

    public VoucherListCallback(@NonNull List<VoucherListItemBean> oldList, @NonNull List<VoucherListItemBean> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }


    @Override
    public int getOldListSize() {
        return oldList==null?0:oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList==null?0:newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
