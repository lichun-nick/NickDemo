package com.inspur.blockchain.voucher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.inspur.blockchain.R;
import com.inspur.blockchain.model.VoucherListItemBean;

import java.util.List;

/**
 * @author lichun
 */
public class VoucherListAdapter extends RecyclerView.Adapter<VoucherListAdapter.ViewHolder> {

    private Context context;
    private List<VoucherListItemBean> mList;
    private OnItemClickListener onItemClickListener;

    public VoucherListAdapter(Context context,List<VoucherListItemBean> mList){
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_voucher,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        VoucherListItemBean bean = mList.get(position);
        holder.tvDid.setText(bean.getDid());
        holder.tvType.setText(bean.getCpt_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null?0:mList.size();
    }

    public void refreshView(List<VoucherListItemBean> mList){
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new VoucherListCallback(this.mList,mList),true);
        this.mList = mList;
        diffResult.dispatchUpdatesTo(this);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivLabel;
        private TextView tvDid;
        private TextView tvType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivLabel = itemView.findViewById(R.id.iv_list_item_voucher_tip);
            tvDid = itemView.findViewById(R.id.tv_list_item_did);
            tvType = itemView.findViewById(R.id.tv_list_item_type);

        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }



    public interface OnItemClickListener{
        void onItemClick(View v,int position);
    }
}
