<template>
  <div>
    <table class="table-purple">
      <thead>
      <tr class="table-purple-header">
        <th>{{ $t('username') }}</th>
        <th>{{ $t('full_name') }}</th>
        <th>{{ $t('account_state') }}</th>
        <th>{{ $t('role') }}</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="user in users" :key="user.fullname" class="table-purple-row" @click="manageUser(user)">
        <td>{{ user.username }}</td>
        <td>{{ user.fullname }}</td>
        <td>{{ user.enabled }}</td>
        <td>{{ user.roles[0] }}</td>
      </tr>
      </tbody>
    </table>
    <p id="update-instruction">{{ $t('update_info') }}</p>
  </div>
</template>

<script>
export default {
  beforeMount() {
    this.changeTitle();
    this.getUsers();
  },
  data() {
    return {
      axios: require('axios'),
      user: {
        id: '',
        enabled: true,
        fullname: '',
        roles: [''],
        username: '',
      },
      users: undefined,
    };
  },
  methods: {
    changeTitle() {
      this.$emit('pageTitle', this.$t('show_users'))
    },
    getUsers() {
      this.axios.get('https://localhost:8181/VMRental/api/users')
          .then(response => {
            this.users = response.data;
          });
    },
    manageUser(user) {
      const modifyId = user.id;
      this.$router.push({
        name: 'manageUser',
        params: {modifyId}
      })
    }
  }
}
</script>

<style>
#update-instruction {
  font-size: 1vw;
  color: gray;
  text-align: right;
}
</style>
