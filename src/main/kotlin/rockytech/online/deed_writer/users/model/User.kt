package rockytech.online.deed_writer.users.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
//
//@Document
// class User(
//    @Id
//    var id: String? = null,
//    var phoneNo: String?,
//    var name: String? = null,
//    var email: String? = null,
//    var passwordhash: String?,
//    var userDto: UserDto? = UserDto(),
//    var authoritiess: List<String> = emptyList(),
//) : UserDetails {
//
//    @JsonIgnore
//    override fun getUsername(): String? {
//        return phoneNo
//    }
//
//    @JsonIgnore
//    override fun getPassword(): String? {
//        return passwordhash
//    }
//
//    @JsonIgnore
//    override fun getAuthorities(): Collection<GrantedAuthority> {
//        return authoritiess.map { SimpleGrantedAuthority(it.toString()) }
//    }
//
//    @JsonIgnore
//    override fun isEnabled(): Boolean {
//        return true
//    }
//
//    @JsonIgnore
//    override fun isAccountNonExpired(): Boolean {
//        return true
//    }
//
//    @JsonIgnore
//    override fun isAccountNonLocked(): Boolean {
//        return true
//    }
//
//    @JsonIgnore
//    override fun isCredentialsNonExpired(): Boolean {
//        return true
//    }
//
//}
@Document
class User(
    @Id
    var id: String? = null,
    var phoneNo: String?,
    var name: String? = null,
    var email: String? = null,
    var passwordhash: String?,
    var userDto: UserDto? = UserDto(),
    var authoritiess: List<String> = emptyList(),
) : UserDetails {

    @JsonIgnore
    override fun getUsername(): String? {
        return phoneNo
    }

    @JsonIgnore
    override fun getPassword(): String? {
        return passwordhash
    }

    @JsonIgnore
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authoritiess
            .filter { it.isNotBlank() }
            .map { SimpleGrantedAuthority(it) }
    }

    @JsonIgnore
    override fun isEnabled(): Boolean {
        return true
    }

    @JsonIgnore
    override fun isAccountNonExpired(): Boolean {
        return true
    }

    @JsonIgnore
    override fun isAccountNonLocked(): Boolean {
        return true
    }

    @JsonIgnore
    override fun isCredentialsNonExpired(): Boolean {
        return true
    }
}


