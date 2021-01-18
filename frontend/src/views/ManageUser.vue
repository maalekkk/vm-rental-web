<template>
<div class="pageContent-container">
    <form class="pageContent-content" v-on:submit.prevent="sendRequest()">
        <p>
            <label>{{ $t('username') }}: </label>
            <input v-model="username" type="text">
        </p>
        <p>
            <label>{{ $t('password') }}: </label>
            <input v-model="password" type="password">
        </p>
        <p>
            <label>{{ $t('full_name') }}: </label>
            <input v-model="fullname" type="text">
        </p>
        <p>
            <label>{{ $t('account_state') }}: </label>
            <input v-model="enabled" type="checkbox">
        </p>
        <p>
            <label>{{ $t('role') }}: </label>
            <input list="roleslist" v-model="role" class="list">
            <datalist id="roleslist">
                <option value="Admin"/>
                <option value="Client"/>
                <option value="Owner"/>
            </datalist>
        </p>
        <p>
            <input type="submit" :value="$t('submit')" >
        </p>
    </form>
</div>
</template>

<script>
export default {
    props: ['user'],
    created() {     
        if (this.user) {
            this.replaceUser(this.user)
        }
    },
    beforeMount() {
        this.changeTitle();
    },
    data () {
        return {
            axios : require('axios'),
            username: '',
            password: '',
            fullname: '',
            enabled: true,
            role: '',
            roles: [],
        };
    },
    methods: {
        changeTitle() {
            this.$emit('pageTitle', this.user ? this.$t('update_user') : this.$t('create_new_user'))
        },
        sendRequest() {
            this.roles.splice(0)
            this.roles.push(this.role)
            if (this.user) {
                this.axios.put('https://localhost:8181/VMRental/api/users/' + this.user.id, {
                    "username": this.username,
                    "password": this.password,
                    "fullname": this.fullname,
                    "enabled": this.enabled,
                    "roles": this.roles,
                })
                .then(response => {
                console.log(response);
                this.$router.push({
                    name: 'showUsers'})})
            }
            else {
                this.axios.post('https://localhost:8181/VMRental/api/users', {
                    "username": this.username,
                    "password": this.password,
                    "fullname": this.fullname,
                    "enabled": this.enabled,
                    "roles": this.roles,
                })
                .then(response => {
                console.log(response);
                this.$router.push({
                    name: 'showUsers'})})
            }
        },
        replaceUser() {
            this.username = this.user.username
            this.password = this.user.password
            this.fullname = this.user.fullname
            this.enabled = this.user.enabled
            this.role = this.user.roles[0]
        }
    },
}
</script>

<style scoped>
    @import './../assets/style/page-content.css';
    @import './../assets/style/page-content-inputs.css';
</style>