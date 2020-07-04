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
import com.inspur.blockchain.model.VerifyHistoryItemBean;
import com.inspur.blockchain.model.VoucherListItemBean;

import java.util.List;

/**
 * @author lichun
 */
public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.ViewHolder> {

    private Context context;
    private List<VerifyHistoryItemBean> mList;
    private OnItemClickListener onItemClickListener;

    public HistoryListAdapter(Context context, List<VerifyHistoryItemBean> mList){
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_verify_history_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        VerifyHistoryItemBean bean = mList.get(position);
        String did = bean.getDid();
        holder.tvDid.setText(String.format("%s****%s", did.substring(did.indexOf(":", 4) + 1, did.indexOf(":", 4) + 5), did.substring(did.length() - 4)));
        holder.tvContent.setText(bean.getTrace_time());
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


    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvDid;
        private TextView tvContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDid = itemView.findViewById(R.id.tv_item_verify_history_title);
            tvContent = itemView.findViewById(R.id.tv_item_verify_history_content);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }



    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }
}
