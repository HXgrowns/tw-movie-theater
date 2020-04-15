const getData = (link) => {
  return axios.get(link);
}

const mvCardContent = data => {
  let title = document.getElementsByTagName('h1');
  title[0].innerHTML = `${data.title} ${data.originalTitle} (${data.year})`;

  let mvPoster = document.getElementById('mv-poster');
  mvPoster.setAttribute('src', `${data.image}`)

  let writers = '可能和导演是一个人';
  let countries = '外国或者中国';
  let languages = '中文或者外语';
  let pubdates = '谁知道什么时候上映呢';

  let directors = data.directors.join(' / ');

  let casts = data.casts.join(' / ');

  let mvInfo = document.getElementById('mv-info');
  mvInfo.innerHTML += `<p>导演：${directors}</p>
  <p>编剧：${writers}</p>
  <p>主演：${casts}</p>
  <p>类型：${data.genres.join(' / ')}</p>
  <p>制片国家/地区：${countries}</p>
  <p>语言：${languages}</p>
  <p>上映日期：${pubdates}</p>
  <p>片长：${data.durations}</p>
  <p>又名：${data.originalTitle}</p>
  `;
}

const mvRatingContent = data => {
  let mvRating = document.querySelector('#rating p strong');
  mvRating.innerHTML = data.rating;

  let sum = 100;

  for(let key = 1; key <= 5; key++) {
    let ratingRec = document.getElementById(`rectangle${key}`);
    let ratingPer = document.getElementById(`rating-percent${key}`);
    let percent = 20 / sum * 100;
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

//这里需要再改一下
const mvCastsContent = data => {

  let mvCasts = document.getElementById('mv-casts');

  mvCasts.innerHTML = `<h2>${data.title}的主要演员· · · · · ·</h2>`;
  let castsIntro = document.createElement('div');
  castsIntro.setAttribute('id', 'casts');

  let link = `http://localhost:8080/movie/findCastsByMovieId/?movieId=${data.id}`;

  axios.get(link).then(response => {
    let castsData = response.data;
    castsData.map(ele => {
      castsIntro.innerHTML += `<div class='cast-unit'>
      <img src="${ele.avatar}" />
      <div class='cast-name'>
        <p>${ele.name}</p>
      </div>
      </div>`;
    });
    mvCasts.appendChild(castsIntro);
  });
}

const otherMv = data => {
  let tag = data.genres;
  let otherlikeMv = document.getElementById('other-like');
  otherlikeMv.innerHTML = `<h2>喜欢这部电影的人也喜欢· · · · · ·</h2>`;
  let otherMvIntro = document.createElement('div');
  otherMvIntro.setAttribute('id', 'otherMv');

  let link = `http://localhost:8080/movie/findByTag/${encodeURI(tag[0])}?start=0&count=12`;
  axios.get(link).then(response => {
    let tagMv = response.data.subjects;
    tagMv.map((ele) => {
      otherMvIntro.innerHTML += `<div class='other-mv-unit' id="${ele.id}" onclick="subPageListen(this)">
        <img src="${ele.image}" />
        <div class='other-mv-title'>
          <p>${ele.title}</p>
          <strong>${ele.rating}</strong>
        </div>
      </div>`;
    });

    otherlikeMv.appendChild(otherMvIntro);
  });
}

const commentShow = data => {
  let commentsDiv = document.getElementById('comments');
  commentsDiv.innerHTML = `<h2>${data.title}的热门评论· · · · · ·</h2>
  <div id="comments-main"></div>
  `;

  let commentsContent = document.getElementById('comments-main');

  let popular_comments = [{
    "rating": 2,
    "author": "饕餮暴血",
    "content": "我算是受够这类型的印度片了，追梦+社会探讨的套路已经有点遭不住了。如果说摔爹在铺垫足够的情况下，结尾的爆发还足够打动人，神秘巨星想仅通过描绘恶劣的现状，理所当然的剧情推进。并辅以喊叫bgm旋转镜头来煽情，未免太儿戏了点。另外，能不能少点mv式片段了，太尬了实在",
    "created_at": "2018-01-19 15:46:33"
    },
    {
    "rating": 2,
    "author": "birds",
    "content": "油管翻译成优兔网的大天朝还是老实玩直播吧，片名也可译为〈我和我的天才女儿〉，跟摔爸多搭。母爱鸡汤挺戳人，可是这蠢蠢马虎的拍法，算计的剧作，看得窝火又漫长。母子俩的脑回路有时真是莫名其妙，小女孩的表演也很是油腻，隐秘的黑罩蓬更沦为形式。有才华脾气糟，能救家等星照耀又泪别离，谁不腻！",
    "created_at": "2018-01-12 17:10:54",
    },
    {
    "rating": 4,
    "author": "西乡一姐小渡边",
    "content": "傅文佩因不堪家暴与陆振华离婚，陆依萍生活所迫与秦五爷签约大上海歌舞厅沦为天涯歌女。",
    "created_at": "2018-01-20 17:19:54",
    },
    {
    "rating": 4,
    "author": "Boxer",
    "content": "后妈要我推荐，选了这部，和曾经家暴的父亲坐在一起看完了，迷之尴尬",
    "created_at": "2018-01-20 22:10:03",
    }]

  popular_comments.forEach((ele) => {
    commentsContent.innerHTML += `<div class="commentItem">
      <div class="comment-author-info">
        <a href="" class="comment-author-name">${ele.author}</a>
        评分：<strong class="comment-rating">${ele.rating}</strong>
        <span class="comment-date">${ele.created_at.split(' ')[0]}</span>
      </div>
      <p class="comment-content">${ele.content}</p>
    </div>
    `
  })
}

window.onload = () => {
  let mvId = document.location.search.slice(4);
  getData(`http://localhost:8080/movie/findById/${mvId}`).then(response => {
    mvCardContent(response.data);
    mvIntroContent(response.data);
    mvCastsContent(response.data);
    mvRatingContent(response.data);
    otherMv(response.data);
    commentShow(response.data)
  });
}