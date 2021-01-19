<template>
  <div class="pageContent-container">
    <form class="pageContent-content" v-on:submit.prevent="sendRequest()">
      <p>
        <label>{{ $t('username') }}: </label>
        <input v-model="username" type="text" :disabled="this.modifyId ? true : false">
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
        <input type="submit" :value="$t('submit')">
      </p>
      <p>
        <label class="error-msg" v-if="this.modifyId ? true : false" v-text="error"/>
      </p>
    </form>
  </div>
</template>

<script>
export default {
  props: ['modifyId'],
  created() {
    if (this.modifyId) {
      this.replaceUser()
    }
  },
  beforeMount() {
    this.changeTitle();
  },
  data() {
    return {
      axios: require('axios'),
      create: true,
      id: '',
      username: '',
      password: '',
      fullname: '',
      enabled: true,
      role: '',
      roles: [],
      error: '',
    };
  },
  methods: {
    changeTitle() {
      this.$emit('pageTitle', this.modifyId ? this.$t('update_user') : this.$t('create_new_user'))
    },
    sendRequest() {
      this.roles.splice(0)
      this.roles.push(this.role)
      if (this.modifyId) {
        this.axios.put('https://localhost:8181/VMRental/api/users/' + this.modifyId, {
          "id": this.id,
          "username": this.username,
          "password": this.password,
          "fullname": this.fullname,
          "enabled": this.enabled,
          "roles": this.roles,
        }).then(
            this.$router.push({
              name: 'showUsers'
            }))
            .catch(this.error = this.$t('error_message'))
      } else {
        this.axios.post('https://localhost:8181/VMRental/api/users', {
          "username": this.username,
          "password": this.password,
          "fullname": this.fullname,
          "enabled": this.enabled,
          "roles": this.roles,
        }).then(
            this.$router.push({
              name: 'showUsers'
            }))
            .catch(this.error = this.$t('error_message'))
      }
    },
    replaceUser() {
      this.axios.get('https://localhost:8181/VMRental/api/users/' + this.modifyId)
          .then(response => {
            this.username = response.data.username
            this.fullname = response.data.fullname
            this.enabled = response.data.enabled
            this.role = response.data.roles[0]
          })

    }
  },
}
</script>

<style scoped>
@import './../assets/style/page-content.css';
@import './../assets/style/page-content-inputs.css';
.error-msg {
  color: purple;
}
</style>