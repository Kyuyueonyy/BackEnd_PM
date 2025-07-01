// import api from 'axios';
import api from '@/api'; //index.js가 import됨

const BASE_URL = '/api/member';
const headers = { 'Content-Type': 'multipart/form-data' };

export default {
  // username 중복 체크, true: 중복(사용불가),  false: 사용 가능
  async checkUsername(username) {
    const { data } = await api.get(`${BASE_URL}/checkusername/${username}`);
    console.log('AUTH GET CHECKUSERNAME', data);
    return data;
  },

  async create(member) {
    // 아바타 파일 업로드 – multipart 인코딩 필요 🡪 FormData 객체 사용

    const formData = new FormData();
    formData.append('username', member.username);
    formData.append('email', member.email);
    formData.append('password', member.password);

    if (member.avatar) {
      formData.append('avatar', member.avatar);
    }

    const { data } = await api.post(BASE_URL, formData, headers);

    console.log('AUTH POST: ', data);
    return data;
  },

  //회원 정보 수정 API
  async update(member) {
    //Form 데이터로 multipart 인코딩
    const formData = new FormData();
    formData.append('username', member.username);
    formData.append('password', member.password); //검증용 현재 비밀번호
    formData.append('email', member.email);

    //아바타 파일이 있는경우에만 추가
    if (member.avatar) {
      formData.append('avatar', member.avatar);
    }

    const { data } = await api.put(
      `${BASE_URL}/${member.username}`,
      formData,
      headers
    );
    console.log('AUTH PUT: ', data);
    return data;
  },

  //비밀번호 변경
  async changePassword(formData) {
    const { data } = await api.put(
      `${BASE_URL}/${formData.username}/changepassword`,
      formData
    );
    console.log('AUTH PUT: ', data);
    return data;
  },
};
