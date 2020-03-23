import axios from 'axios';
const baseUrl = `${window.location.href}network`;

const guessOutput = async data => {
  const response = await axios.post(`${baseUrl}/guess`, data, {
    headers: { 'Content-Type': 'text/plain' },
    withCredentials: true
  });
  return response.data;
}

const getNetwork = async () => {
  const response = await axios.get(baseUrl, {
    withCredentials: true
  });
  return response.data;
}

const createNetwork = async network => {
  const response = await axios.post(baseUrl, network, {
    withCredentials: true
  });
  return response.data;
}

const trainNetwork = async () => {
  const response = await axios.put(baseUrl,null, {
    withCredentials: true
  });
  return response.data;
}

const getAccuracy = async () => {
  const response = await axios.get(`${baseUrl}/accuracy`, {
    withCredentials: true
  })
  return response.data;
}

export default {guessOutput, createNetwork, trainNetwork, getNetwork, getAccuracy}

