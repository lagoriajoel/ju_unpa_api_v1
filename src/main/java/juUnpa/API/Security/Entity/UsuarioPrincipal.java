package juUnpa.API.Security.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioPrincipal implements UserDetails {

        private String nombre;

        private String nombreUsuario;

        private String password;
        private Collection<? extends GrantedAuthority> authorities;

        public UsuarioPrincipal(String nombre, String nombreUsuario, String password, Collection<? extends GrantedAuthority> authorities) {

            this.nombre=nombre;
            this.nombreUsuario = nombreUsuario;
            this.password = password;
            this.authorities = authorities;
        }

        public static UsuarioPrincipal build(Usuario usuario){
            List<GrantedAuthority> authorities =
                    usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol
                            .getRolNombre().name())).collect(Collectors.toList());
            return new UsuarioPrincipal(usuario.getNombre(), usuario.getNombreUsuario(), usuario.getPassword(), authorities);
        }



    @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        @Override
        public String getPassword() {
            return password;
        }


    public String getNombre() {
        return nombre;
    }

        @Override
        public String getUsername() {
            return nombreUsuario;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }




}
