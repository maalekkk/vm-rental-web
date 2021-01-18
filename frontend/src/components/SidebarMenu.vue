<template>
    <div class="sidebarMenu">
        <ul class="menu">
            <li class="menu-item">
                <router-link to="/">
                    <div class="iconBlock">
                        <i class="fa fa-home icon"/>
                    </div>
                    <label>{{ $t('main_page') }}</label>
                </router-link>
            </li>
            <li class="menu-item">
                <router-link to="/showUsers">
                    <div class="iconBlock">
                        <i class="fa fa-users icon"/>
                    </div>
                    <label>{{ $t('show_users') }}</label>
                </router-link>
            </li>
            <li class="menu-item">
                <router-link to="/manageUser">
                    <div class="iconBlock">
                        <i class="fa fa-user-plus icon"/>
                    </div>
                    <label>{{ $t('manage_user') }}</label>
                </router-link>
            </li>
            <li class="menu-item">
                <router-link to="/about">
                    <div class="iconBlock">
                        <i class="fa fa-info icon"/>
                    </div>
                    <label>{{ $t('app_info_title') }}</label>
                </router-link>
            </li>
        </ul>
    </div>
</template>

<script>
export default {}
</script>

<style>
</style>