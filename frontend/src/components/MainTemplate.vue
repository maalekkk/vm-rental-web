<template>
    <div class="container">
        <div class="leftSide">
            <logo/>
            <div style="border-top:1px solid rgba(69, 74, 84, 0.1)"/>
            <sidebar-menu/>
        </div>
        <div class="rightSide">
            <div class="header">
                <h3 class="header-title">{{ $t('project_name') }} - SPA</h3>
            </div>
            <div style="border-top:1px solid rgba(50, 50, 50, 0.999)"/>
            <div class="title">
                <h2>{{ title }}</h2>
            </div>
            <div class="pageContent">
                <router-view @pageTitle="setTitle"/>
            </div>
        </div>
        <my-footer/>
    </div>
</template>

<script>
import SidebarMenu from './SidebarMenu.vue';
import Logo from './Logo.vue';
import Footer from './Footer.vue';
export default {
    components: {
        SidebarMenu,
        Logo,
        'my-footer': Footer,
    },   
    data() {
        return {
            title: '',
        };
    },
    methods: {
        getUsers() {
            this.axios.get('https://localhost:8181/VMRental/api/users')
            .then(response => {
            console.log(response);
            this.users = response.data;
            })
        },
        setTitle(val) {
            this.title = val;
        }
    }
}
</script>

<style>
  @import './../assets/style/table.css';
  @import './../assets/style/logo.css';
  @import './../assets/style/fonts.css';
  @import './../assets/style/header.css';
  @import './../assets/style/main-template.css';
  @import './../assets/style/sidebar-menu.css';
</style>
