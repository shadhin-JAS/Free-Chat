package com.cscomer.freechat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cscomer.freechat.databinding.FragmentProfileBinding
import com.cscomer.freechat.databinding.UserItemBinding

class UserAdapter(var itemClick:ItemClick) : ListAdapter<user,UserViewHolder>(comparator) {
    interface ItemClick{

        fun onItemClick(user:user)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
     return UserViewHolder(UserItemBinding.
        inflate(LayoutInflater.
           from(parent.context),
                parent,false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {


        getItem(position).let {

            holder.binding.apply {


                nameTV.text=it.Nameid
                gmailTV.text=it.gmailid
                BIO.text=it.bioid


            }
            holder.itemView.setOnClickListener{_->
                itemClick.onItemClick(it)

            }

        }



    }
companion object{

    var comparator=object : DiffUtil.ItemCallback<user>(){
        override fun areItemsTheSame(oldItem: user, newItem: user): Boolean {
           return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: user, newItem: user): Boolean {
         return oldItem==newItem
        }


    }
}

}


class UserViewHolder(val binding :UserItemBinding): RecyclerView.ViewHolder(binding.root){


}