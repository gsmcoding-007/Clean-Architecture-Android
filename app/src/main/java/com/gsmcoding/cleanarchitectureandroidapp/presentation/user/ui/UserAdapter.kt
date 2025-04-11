import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.gsmcoding.cleanarchitectureandroidapp.R
import com.gsmcoding.cleanarchitectureandroidapp.databinding.ItemUserBinding
import com.gsmcoding.cleanarchitectureandroidapp.domain.model.User

//class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
//
//    private val userList = mutableListOf<User>()
//
//    fun submitList(users: List<User>) {
//        userList.clear()
//        userList.addAll(users)
//        notifyDataSetChanged()
//    }
//
//    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val nameText = itemView.findViewById<TextView>(R.id.nameText)
//        private val emailText = itemView.findViewById<TextView>(R.id.emailText)
//
//        fun bind(user: User) {
//            nameText.text = user.name
//            emailText.text = user.email
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_user, parent, false)
//        return UserViewHolder(view)
//    }
//
//    override fun getItemCount() = userList.size
//
//    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
//        holder.bind(userList[position])
//    }
//}

class UserAdapter : ListAdapter<User, UserAdapter.UserViewHolder>(DiffCallback()) {

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.txtName.text = user.name
            binding.txtEmail.text = user.email
            binding.imgProfile.load(user.profileUrl) {
                placeholder(R.drawable.ic_user_placeholder)
                error(R.drawable.ic_user_placeholder)
                transformations(CircleCropTransformation())
            }
//            Glide.with(binding.imageView.context)
//                .load(user.profileUrl)
//                .placeholder(R.drawable.ic_user_placeholder)
//                .into(binding.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(old: User, new: User) = old.uid== new.uid
        override fun areContentsTheSame(old: User, new: User) = old == new
    }
}

