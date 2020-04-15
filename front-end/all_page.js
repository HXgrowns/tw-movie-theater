const banner = (i = 2) => {
  let bannerImg = document.querySelectorAll('.banner-part');

  let bannerImgCount = bannerImg.length;
  let autoBanner = setInterval(() => {
    if (i === 1) {
      bannerSlide(1, bannerImgCount);
      i += 1;
    }else if (i > 1 && i < bannerImgCount) {
      bannerSlide(i, (i - 1));
      i += 1;
    }else if (i === bannerImgCount) {
      bannerSlide(bannerImgCount, (bannerImgCount - 1));
      i = 1;
    }
  }, 3000);

  bannerClick(autoBanner);
}

const bannerSlide = (slideInId, slideOutId) => {
  let slideOutImg = document.getElementById(`banner-part${slideOutId}`);
  let slideOutLink = document.getElementById(`banner-link${slideOutId}`);
  let slideInImg = document.getElementById(`banner-part${slideInId}`);
  let slideInLink = document.getElementById(`banner-link${slideInId}`);

  slideInLink.style.backgroundColor = "rgba(255, 255, 255, 0.88)";
  slideOutLink.style.backgroundColor = "rgba(255, 255, 255, 0.6)";

  if (slideOutId === 1) {
    document.getElementById(`banner-part5`).classList.remove('slide-out');
  }else {
    document.getElementById(`banner-part${slideOutId - 1}`).classList.remove('slide-out');
  }

  slideOutImg.classList.add('slide-out');
  slideOutImg.classList.remove('slide-in');
  slideInImg.classList.add('slide-in');
  slideOutImg.style.left = "1160px";
}

const bannerClick = autoBanner => {
  let bannerLink = document.getElementById(`banner-link`);
  let bannerImg = document.querySelectorAll(`.banner-part`);
  let bannerImgCount = bannerImg.length;

  bannerLink.addEventListener('click', (event) => {
    clearInterval(autoBanner);

    let bannerNow = document.querySelector('.slide-in');
    let bannerNowId = Number(bannerNow.id.charAt(bannerNow.id.length - 1));
    let bannerToId = Number(event.target.id.charAt(event.target.id.length - 1));

    let mouseOverSlide = setInterval(() => {
      if (bannerNowId === 1) {
        bannerSlide(1, bannerImgCount);
        bannerNowId += 1;
      }else if (bannerNowId > 1 && bannerNowId < bannerImgCount) {
        bannerSlide(bannerNowId, (bannerNowId - 1));
        bannerNowId += 1;
      }else if (bannerNowId === bannerImgCount) {
        bannerSlide(bannerImgCount, (bannerImgCount - 1));
        bannerNowId = 1;
      }

      if (bannerToId === bannerImgCount) {
        if (bannerNowId === 1) {
          clearInterval(mouseOverSlide);
          return banner(bannerToId);
        }
      }else {
        if (bannerNowId === bannerToId + 1) {
          clearInterval(mouseOverSlide);
          return banner(bannerToId);
        }
      }
    }, 200);
  });
}

const contentShow = (data) => {
  let content = document.getElementById('content');
  content.removeAttribute('class');

  content.innerHTML = `
  <div id="mv-content" class="content-all"></div>
  <div id="page-turning">
    <div id="page-number"></div>
  </div>
  `;

  let mvContent = document.getElementById('mv-content');
  mvContent.innerHTML = '';

  data.map((ele) => {
    mvContent.innerHTML += `<div class='mv-unit' id="${ele.id}" onclick="subPageListen(this)">
      <img src="${ele.image}" />
      <div class='mv-title'>
        <p>${ele.title}</p>
        <strong>${ele.rating}</strong>
      </div>
    </div>
  `;
  });
}

const recommendShow = data => {
  let content = document.getElementById('content');

  let recommendContent = document.createElement('div');
  recommendContent.setAttribute('class', 'recommend-content')
  recommendContent.innerHTML = `<p class="recommend-title">${data.title}</p>`;

  let recommendPart =document.createElement('div');
  recommendPart.setAttribute('class', 'recommend-part');

  data.subjects.map((ele) => {
    recommendPart.innerHTML += `<div class="mv-unit" onclick="subPageListen(this)" id="${ele.id}">
      <img src="${ele.images}" />
      <div class='mv-title'>
        <p>${ele.title}</p>
        <strong>${ele.rating}</strong>
      </div>
    </div>
    `
  });

  recommendContent.appendChild(recommendPart);
  content.appendChild(recommendContent);
}

const recommendListen = () => {
  let content = document.getElementById('content');
  content.innerHTML = '';
  let recommendlink = ['http://localhost:8080/movie/findByClassification/in_theater?start=0&count=12',
    'http://localhost:8080/movie/findByClassification/coming_soon?start=0&count=12',
    'http://localhost:8080/movie/findByClassification/top250?start=0&count=12'
  ];

  recommendlink.map(link => {
    axios.get(link).then(response => {
      recommendShow(response.data);
    });
  });
}

const subPageListen = button => {
  let unitId = Number(button.id);
  window.open(`sub_page.html?id=${unitId}`);
}

const pageTurningStyle = (pageNow, totalPage) => {
  let pageTurningDiv = document.getElementById('page-number');
  pageTurningDiv.innerHTML = '';

  if (totalPage > 7) {
    if (pageNow <= 3) {
      for (let i = 1; i <= 5; i++) {
        pageTurningDiv.innerHTML += `<div class="pageButton" onclick="pageTurning(this)" id="page${i}">
          <button>${i}</button>
        </div>`;
      }
      pageTurningDiv.innerHTML += `<div class="pageButton" id="page-ellipsis">
      ...
      </div>
      <div class="pageButton" onclick="pageTurning(this)" id="page${totalPage}">
        <button>${totalPage}</button>
      </div>`;
    }
  
    if (pageNow === 4) {
      for (let i = 1; i <= 6; i++) {
        pageTurningDiv.innerHTML += `<div class="pageButton" onclick="pageTurning(this)" id="page${i}">
          <button>${i}</button>
        </div>`;
      }
      pageTurningDiv.innerHTML += `<div class="pageButton" id="page-ellipsis">
      ...
      </div>
      <div class="pageButton" onclick="pageTurning(this)" id="page${totalPage}">
        <button>${totalPage}</button>
      </div>`;
    }
  
    if (pageNow > 4 && pageNow < (totalPage - 3)) {
      pageTurningDiv.innerHTML += `<div class="pageButton" onclick="pageTurning(this)" id="page1">
        <button>1</button>
      </div>
      <div class="pageButton" id="page-ellipsis">
      ...
      </div>`;
  
      for (let i = pageNow - 2; i <= pageNow + 2; i++) {
        pageTurningDiv.innerHTML += `<div class="pageButton" onclick="pageTurning(this)" id="page${i}">
          <button>${i}</button>
        </div>`;
      }
  
      pageTurningDiv.innerHTML += `<div class="pageButton" id="page-ellipsis">
      ...
      </div>
      <div class="pageButton" onclick="pageTurning(this)" id="page${totalPage}">
        <button>${totalPage}</button>
      </div>`;
    }
  
    if (pageNow === (totalPage - 3)) {
      pageTurningDiv.innerHTML += `<div class="pageButton" onclick="pageTurning(this)" id="page1">
      <button>1</button>
      </div>
      <div class="pageButton" id="page-ellipsis">
      ...
      </div>`;
  
      for (let i = totalPage - 5; i <= totalPage; i++) {
        pageTurningDiv.innerHTML += `<div class="pageButton" onclick="pageTurning(this)" id="page${i}">
          <button>${i}</button>
        </div>`;
      }
    }
  
    if (pageNow > (totalPage - 3)) {
      pageTurningDiv.innerHTML += `<div class="pageButton" onclick="pageTurning(this)" id="page1">
      <button>1</button>
      </div>
      <div class="pageButton" id="page-ellipsis">
      ...
      </div>`;
  
      for (let i = totalPage - 4; i <= totalPage; i++) {
        pageTurningDiv.innerHTML += `<div class="pageButton" onclick="pageTurning(this)" id="page${i}">
          <button>${i}</button>
        </div>`;
      }
    }
  }

  if (totalPage <= 7) {
    for (let i = 1; i <= totalPage; i++) {
      pageTurningDiv.innerHTML += `<div class="pageButton" onclick="pageTurning(this)" id="page${i}">
        <button>${i}</button>
      </div>`;
    }
  }

  let pageButtonNow = document.getElementById(`page${pageNow}`);
  pageButtonNow.style.backgroundColor = '#00a1d6';
  pageButtonNow.style.borderColor = '#00a1d6';
  pageButtonNow.firstElementChild.style.color = '#fff';

  if (totalPage > 1) {
    let pageTurning = document.getElementById('page-turning');
    pageTurning.innerHTML += `<div class="pageButton" onclick="nextPage()" id="next-page" name="next${pageNow}">
      <button>下一页</button>
    </div>`;
  }
}

const nextPage = () => {
  let page = Number(document.getElementById('next-page').getAttribute('name').substring(4)) + 1;
  whichWinType(page);
}

const pageTurning = pageButton => {
  let page = 1;
  if(pageButton) {
    page = Number(pageButton.firstElementChild.innerHTML);
  }
  whichWinType(page);
}

const search = () => {
  let searchContent = document.getElementById('search-input').value;
  return window.open(`all_page.html?keyword=${searchContent}`);
}

const whichWinType = page => {
  let pageSuffix = decodeURIComponent(document.location.search);

  if (pageSuffix === '') {
    let link = `http://localhost:8080/movie/findAllByPage/?start=${(page - 1) * 24}&count=24`;
    axios.get(link).then(response => {
      let mvData = response.data.subjects;
      let totalCount = response.data.count[0];
      show24Item(mvData, page, totalCount);
    });
  }

  if (pageSuffix.split('=')[0] === '?keyword') {
    searchPage(page, pageSuffix.split('=')[1]);
  }
  
  if (pageSuffix.split('=')[0] === '?tag') {
    tagPage(page, pageSuffix.split('=')[1]);
  }
}

const searchPage = (page, keyword) => {
  let link = `http://localhost:8080/movie/findByKeyword/?keyword=${keyword}&start=${(page - 1) * 24}&count=24`;
  axios.get(link).then(response => {
    let mvData = response.data.subjects;
    let totalCount = response.data.count[0];
    if (mvData.length === 0) {
      document.getElementById('content').innerHTML = '没有找到任何相关影片';
    }else {
      show24Item(mvData, page, totalCount);
    }
  });
}

const tagPage = (page, tag) => {
  let link = `http://localhost:8080/movie/findByTag/${encodeURI(tag)}?start=${(page - 1) * 24}&count=24`;
  axios.get(link).then(response => {
    let mvData = response.data.subjects;
    let totalCount = response.data.count;
    if (mvData.length === 0) {
      document.getElementById('content').innerHTML = '没有找到任何相关影片';
    }else {
      show24Item(mvData, page, totalCount);
    }
  });
}

const show24Item = (data, page, totalCount) => {
  contentShow(data);
  let pageTotalCount = Math.ceil(totalCount / 24);
  pageTurningStyle(page, pageTotalCount);
  if (pageTotalCount > 1) {
    return (document.getElementById('next-page').setAttribute('name', `next${page}`));
  }
}

const classified = button => {
  return window.open(`all_page.html?tag=${button.innerHTML}`);
}

const allOpen = () => {
  return window.open('all_page.html');
}

window.onload = function () {
  pageTurning();
  banner();
}