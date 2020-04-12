const getData = (link) => {
  return axios.get(link);
}

const mvCardContent = data => {
  let title = document.getElementsByTagName('h1');
  title[0].innerHTML = `${data.title} ${data.original_title} (${data.year})`;

  let mvPoster = document.getElementById('mv-poster');
  mvPoster.setAttribute('src', `${data.images.small}`)

  let writers = data.writers.map((ele) => {
    return ele.name;
  }).join(' / ');

  let directors = data.directors.map((ele) => {
    return ele.name;
  }).join(' / ');

  let casts = data.casts.map((ele) => {
    return ele.name;
  }).join(' / ');

  let mvInfo = document.getElementById('mv-info');
  mvInfo.innerHTML += `<p>导演：${directors}</p>
  <p>编剧：${writers}</p>
  <p>主演：${casts}</p>
  <p>类型：${data.genres.join(' / ')}</p>
  <p>制片国家/地区：${data.countries.join(' / ')}</p>
  <p>语言：${data.languages.join(' / ')}</p>
  <p>上映日期：${data.pubdates.join(' / ')}</p>
  <p>片长：${data.durations.join(' / ')}</p>
  <p>又名：${data.aka.join(' / ')}</p>
  `

  let comments = document.getElementById('comments');
}

const mvRatingContent = data => {
  let mvRating = document.querySelector('#rating p strong');
  mvRating.innerHTML = data.rating.average;

  let sum = 0;
  for (let key in data.rating.details) {
    sum += Number(data.rating.details[key]);
  }

  for(let key in data.rating.details) {
    let ratingRec = document.getElementById(`rectangle${key}`);
    let ratingPer = document.getElementById(`rating-percent${key}`);
    let percent = (Number(data.rating.details[key])/sum) * 100;
    ratingRec.style.width = `${percent}px`;
    ratingPer.innerHTML = `${percent.toFixed(1)}%`;
  }

  let doubanLink = document.getElementById('douban-link');
  doubanLink.setAttribute('href', `${data.alt}`);
}

const mvIntroContent = data => {
  let mvIntro = document.getElementById('mv-intro');
  mvIntro.innerHTML = `<h2>${data.title}的剧情介绍· · · · · ·</h2>`
  let summary = data.summary.split('\n');
  summary.map((ele) => {
    mvIntro.innerHTML += `<p>${ele}</p>`
  });
}

const mvCastsContent = data => {
  let mvCasts = document.getElementById('mv-casts');

  mvCasts.innerHTML = `<h2>${data.title}的主要演员· · · · · ·</h2>`;
  let castsIntro = document.createElement('div');
  castsIntro.setAttribute('id', 'casts');
  data.casts.map((ele) => {
    castsIntro.innerHTML += `<div class='cast-unit'>
    <img src="${ele.avatars.small}" />
    <div class='cast-name'>
      <p>${ele.name_en}</p>
    </div>
  </div>`;
  });
  mvCasts.appendChild(castsIntro);
}

const otherMv = data => {
  let tag = data.genres;

  let allData = JSON.parse(sessionStorage.getItem('key'));
  let tagMv = [];

  tag.forEach(tagEle => {
    let genreMv = allData.filter(ele => {
      return (ele.genres.indexOf(tagEle) !== -1);
    });

    tagMv = tagMv.concat(genreMv);
  });

  tagMv.map((ele, index) => {
    if (tagMv.indexOf(ele) !== tagMv.lastIndexOf(ele)) {
      tagMv.splice(index, 1);
    }
  });

  let otherlikeMv = document.getElementById('other-like');
  otherlikeMv.innerHTML = `<h2>喜欢这部电影的人也喜欢· · · · · ·</h2>`;
  let otherMvIntro = document.createElement('div');
  otherMvIntro.setAttribute('id', 'otherMv');

  tagMv.slice(0, 12).map((ele) => {
    otherMvIntro.innerHTML += `<div class='other-mv-unit' id="${ele.id}" onclick="subPageListen(this)">
      <img src="${ele.images.small}" />
      <div class='other-mv-title'>
        <p>${ele.title}</p>
        <strong>${ele.rating.average}</strong>
      </div>
    </div>`;
  });
  
  otherlikeMv.appendChild(otherMvIntro);
}

const commentShow = data => {
  let commentsDiv = document.getElementById('comments');
  commentsDiv.innerHTML = `<h2>${data.title}的热门评论· · · · · ·</h2>
  <div id="comments-main"></div>
  `;

  let commentsContent = document.getElementById('comments-main');
  data.popular_comments.forEach((ele) => {
    commentsContent.innerHTML += `<div class="commentItem">
      <div class="comment-author-info">
        <a href="" class="comment-author-name">${ele.author.name}</a>
        评分：<strong class="comment-rating">${ele.rating.value}</strong>
        <span class="comment-date">${ele.created_at.split(' ')[0]}</span>
      </div>
      <p class="comment-content">${ele.content}</p>
    </div>
    `
  })
}

window.onload = () => {
  let mvId = document.location.search.slice(4);
  
  getData(`https://douban.uieee.com/v2/movie/subject/${mvId}`).then(response => {
    mvCardContent(response.data);
    mvIntroContent(response.data);
    mvCastsContent(response.data);
    mvRatingContent(response.data);
    otherMv(response.data);
    commentShow(response.data)
  });
}