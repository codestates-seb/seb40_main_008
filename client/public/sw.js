if(!self.define){let e,a={};const c=(c,i)=>(c=new URL(c+".js",i).href,a[c]||new Promise((a=>{if("document"in self){const e=document.createElement("script");e.src=c,e.onload=a,document.head.appendChild(e)}else e=c,importScripts(c),a()})).then((()=>{let e=a[c];if(!e)throw new Error(`Module ${c} didn’t register its module`);return e})));self.define=(i,f)=>{const s=e||("document"in self?document.currentScript.src:"")||location.href;if(a[s])return;let t={};const d=e=>c(e,s),n={module:{uri:s},exports:t,require:d};a[s]=Promise.all(i.map((e=>n[e]||d(e)))).then((e=>(f(...e),t)))}}define(["./workbox-588899ac"],(function(e){"use strict";importScripts("fallback-SvZZugCeYTn2kDTQtIrpg.js"),self.skipWaiting(),e.clientsClaim(),e.precacheAndRoute([{url:"/_next/app-build-manifest.json",revision:"e7aace1dbaacafb2a5385d24845ee281"},{url:"/_next/static/SvZZugCeYTn2kDTQtIrpg/_buildManifest.js",revision:"f9efa3580222c374a4e8d0feb06e79ea"},{url:"/_next/static/SvZZugCeYTn2kDTQtIrpg/_ssgManifest.js",revision:"b6652df95db52feb4daf4eca35380933"},{url:"/_next/static/chunks/17-861cdabe41c739c8.js",revision:"SvZZugCeYTn2kDTQtIrpg"},{url:"/_next/static/chunks/206.c43937cbeaf4ba1c.js",revision:"c43937cbeaf4ba1c"},{url:"/_next/static/chunks/247.914eedc55c3cb85a.js",revision:"914eedc55c3cb85a"},{url:"/_next/static/chunks/254-fc916d09d824db9a.js",revision:"SvZZugCeYTn2kDTQtIrpg"},{url:"/_next/static/chunks/299-b62d8b0b1e1c4af0.js",revision:"SvZZugCeYTn2kDTQtIrpg"},{url:"/_next/static/chunks/30-002c6d2f92137227.js",revision:"SvZZugCeYTn2kDTQtIrpg"},{url:"/_next/static/chunks/53.16e6b84710dbb520.js",revision:"16e6b84710dbb520"},{url:"/_next/static/chunks/621.902f287faa3b746a.js",revision:"902f287faa3b746a"},{url:"/_next/static/chunks/686.a025b1846b15aa93.js",revision:"a025b1846b15aa93"},{url:"/_next/static/chunks/690.c6b4a259b4093e0b.js",revision:"c6b4a259b4093e0b"},{url:"/_next/static/chunks/726.22f7b640eb159c4a.js",revision:"22f7b640eb159c4a"},{url:"/_next/static/chunks/753.c4535dd981d2ebc7.js",revision:"c4535dd981d2ebc7"},{url:"/_next/static/chunks/867.ebb0236632f363cc.js",revision:"ebb0236632f363cc"},{url:"/_next/static/chunks/90-2b309b96ee3b9b34.js",revision:"SvZZugCeYTn2kDTQtIrpg"},{url:"/_next/static/chunks/973.40d2c8fb65b4f336.js",revision:"40d2c8fb65b4f336"},{url:"/_next/static/chunks/app/categories/%5Bcategory%5D/page-f2e823f279b31ac6.js",revision:"SvZZugCeYTn2kDTQtIrpg"},{url:"/_next/static/chunks/app/categories/page-b789a9cc690c8390.js",revision:"SvZZugCeYTn2kDTQtIrpg"},{url:"/_next/static/chunks/app/charge/page-83d538b7f08d5a5b.js",revision:"SvZZugCeYTn2kDTQtIrpg"},{url:"/_next/static/chunks/app/detail/%5BquestionId%5D/page-84f9eff0d48dce0c.js",revision:"SvZZugCeYTn2kDTQtIrpg"},{url:"/_next/static/chunks/app/detail/layout-c193a9165a9fd843.js",revision:"SvZZugCeYTn2kDTQtIrpg"},{url:"/_next/static/chunks/app/detail/page-a8fc766c04d7daee.js",revision:"SvZZugCeYTn2kDTQtIrpg"},{url:"/_next/static/chunks/app/layout-e6bf10f81074730e.js",revision:"SvZZugCeYTn2kDTQtIrpg"},{url:"/_next/static/chunks/app/login/page-c9228ac237b1432c.js",revision:"SvZZugCeYTn2kDTQtIrpg"},{url:"/_next/static/chunks/app/mypage/page-cd8acab054567e9b.js",revision:"SvZZugCeYTn2kDTQtIrpg"},{url:"/_next/static/chunks/app/page-4ee60c53510fbafc.js",revision:"SvZZugCeYTn2kDTQtIrpg"},{url:"/_next/static/chunks/app/search/page-d24297dc0099becd.js",revision:"SvZZugCeYTn2kDTQtIrpg"},{url:"/_next/static/chunks/app/upload/page-5de10c4473a98c3d.js",revision:"SvZZugCeYTn2kDTQtIrpg"},{url:"/_next/static/chunks/c16184b3-93dab08875f13cc8.js",revision:"SvZZugCeYTn2kDTQtIrpg"},{url:"/_next/static/chunks/main-9a99a9871f65d125.js",revision:"SvZZugCeYTn2kDTQtIrpg"},{url:"/_next/static/chunks/main-app-5dbe79f35bd947e6.js",revision:"SvZZugCeYTn2kDTQtIrpg"},{url:"/_next/static/chunks/pages/_app-dae832aa212cbf37.js",revision:"SvZZugCeYTn2kDTQtIrpg"},{url:"/_next/static/chunks/pages/_error-1fd6c3782812bbc4.js",revision:"SvZZugCeYTn2kDTQtIrpg"},{url:"/_next/static/chunks/pages/_offline-0092ab6fc302e6f3.js",revision:"SvZZugCeYTn2kDTQtIrpg"},{url:"/_next/static/chunks/polyfills-c67a75d1b6f99dc8.js",revision:"837c0df77fd5009c9e46d446188ecfd0"},{url:"/_next/static/chunks/webpack-d5ee152e00589b25.js",revision:"SvZZugCeYTn2kDTQtIrpg"},{url:"/_next/static/css/12807a3c021a660f.css",revision:"12807a3c021a660f"},{url:"/_next/static/css/17b5d5a6925e026c.css",revision:"17b5d5a6925e026c"},{url:"/_next/static/css/21d6c2c6d4c1e15c.css",revision:"21d6c2c6d4c1e15c"},{url:"/_next/static/css/23ac748fee846b2c.css",revision:"23ac748fee846b2c"},{url:"/_next/static/css/3a2fd7daa987480b.css",revision:"3a2fd7daa987480b"},{url:"/_next/static/css/3fe5d0c53cbbb3bd.css",revision:"3fe5d0c53cbbb3bd"},{url:"/_next/static/css/491156b131c6e484.css",revision:"491156b131c6e484"},{url:"/_next/static/css/66759b8e9b98654d.css",revision:"66759b8e9b98654d"},{url:"/_next/static/css/77278a07d3125721.css",revision:"77278a07d3125721"},{url:"/_next/static/css/7eaa510069b2a976.css",revision:"7eaa510069b2a976"},{url:"/_next/static/css/9de5a5d045ebff04.css",revision:"9de5a5d045ebff04"},{url:"/_next/static/css/a17721e6e7cc3a6a.css",revision:"a17721e6e7cc3a6a"},{url:"/_next/static/css/a8ee84253ea4ea6d.css",revision:"a8ee84253ea4ea6d"},{url:"/_next/static/css/e3561a2422685620.css",revision:"e3561a2422685620"},{url:"/_next/static/css/e8dee15a8611ac30.css",revision:"e8dee15a8611ac30"},{url:"/_next/static/css/ebdaa507681b0036.css",revision:"ebdaa507681b0036"},{url:"/_next/static/media/0053c907570eafc8.woff2",revision:"5176be684d32e142ca7c6418c3673f8e"},{url:"/_next/static/media/00f070192bccd2fc.woff2",revision:"1b5261fd8149e11eae3968a7827115b8"},{url:"/_next/static/media/0541070d9131eed9.woff2",revision:"de552b3f8c2d435b449c6c214b587207"},{url:"/_next/static/media/05862983fb5402a9.woff2",revision:"bb2ee66c4fea7a888ce6f39bd48e6c3b"},{url:"/_next/static/media/05ac32b0628f2ea1.woff2",revision:"78bdb106f95f6d496758aa7566133eea"},{url:"/_next/static/media/090ffa3987404bec.woff2",revision:"ad943d86d481ddfeeb4ba875ca62045f"},{url:"/_next/static/media/0dbcb68b2de33a23.woff2",revision:"29d31e50dc52291b391bf2a0820a6deb"},{url:"/_next/static/media/0dd7d1cdeeb0109e.woff2",revision:"7f6871a52517983fe5b8afbac8454e41"},{url:"/_next/static/media/0e2ceea09e066a91.woff2",revision:"27a5c52d479d397956c1d684426ad525"},{url:"/_next/static/media/11f70c384ee2a3d1.woff2",revision:"27c6c6ee2babab5a437202bde7ce9880"},{url:"/_next/static/media/120ffb681985658d.woff2",revision:"bb8501158a369d7e894aaed82c7695f8"},{url:"/_next/static/media/123aa5c2d8fa516e.woff2",revision:"c2f6904cb9eda84143e3d71fd659aefe"},{url:"/_next/static/media/161e4503c7666aa2.woff2",revision:"a3c7c1d7ff4ef98dbb0087c118990f7d"},{url:"/_next/static/media/17f3ad67644e4e8b.woff2",revision:"96db5e4e9106da50aa2d43db1a6990f2"},{url:"/_next/static/media/1824637be0e66bc8.woff2",revision:"482ebeb27c56c710490bbdfc03ef98c8"},{url:"/_next/static/media/18c538f9677e3dc9.woff2",revision:"af5ff5636317b4fc949f507fae56d365"},{url:"/_next/static/media/1e8d76f218a5cbd6.woff2",revision:"3592fdbe380b9c1cfa19e91a4248fb40"},{url:"/_next/static/media/20a0bcc81166f509.woff2",revision:"ae716695b352f7db24f3be23175273b4"},{url:"/_next/static/media/232d054c57d4a06f.woff2",revision:"81fa94bdfa76fd468c0ab2cc9c7a774d"},{url:"/_next/static/media/27037a1cec918502.woff2",revision:"c59fba0927b580a9de2518f87c6306bf"},{url:"/_next/static/media/2ba6386799918b18.woff2",revision:"2b7dbd9d339ace04daa28e7cdb41e0b8"},{url:"/_next/static/media/2c8c595d16b7e654.woff2",revision:"8de72dd414cefd268b01b73d857eb941"},{url:"/_next/static/media/2ce1bdd8dacef339.woff2",revision:"b923cfd74c4b71e962d86f26a0b3f6ed"},{url:"/_next/static/media/2d842db087769003.woff2",revision:"bd8612e8f52f0d9e0c22eba216f72ae6"},{url:"/_next/static/media/2e00479dc6257870.woff2",revision:"c5ec71016ea1e0075f85b50c7f2a8d87"},{url:"/_next/static/media/2f61bd4ff1a42491.woff2",revision:"ceff0bb5d5e12650ed6fc21cc055a759"},{url:"/_next/static/media/34b4eaf6641c912c.woff2",revision:"45446cc8d156b7781e7b83cdecee6395"},{url:"/_next/static/media/387e68a248ea051e.woff2",revision:"07c581c3235e227ea1b144fc7c34c77c"},{url:"/_next/static/media/3ae623a3a0eff338.woff2",revision:"214c1474b9b63ed45dd7fc77c13ffb28"},{url:"/_next/static/media/3b01196dbd4d03aa.woff2",revision:"c9a20d1ca5934a211c818f5359f7a383"},{url:"/_next/static/media/3d71535b3e680c7d.woff2",revision:"d18973ec9b1436fb967e25c41c0bcb08"},{url:"/_next/static/media/3e672ce47b193d87.woff2",revision:"99f3b86fa21316a0909691a4c275651a"},{url:"/_next/static/media/3eb74f05d7629aac.woff2",revision:"fd39d3e610042f644673504f2679784f"},{url:"/_next/static/media/443ab767a58ae800.woff2",revision:"8ad9627e1f6398247927ba71633cf692"},{url:"/_next/static/media/4466d967f5b46a1d.woff2",revision:"884e2bf8665051cefeea90cccd474815"},{url:"/_next/static/media/4833fb70bac957a2.woff2",revision:"4492b6c80d52e3a5b8f2e14031141e16"},{url:"/_next/static/media/485d9046c1c2f4fc.woff2",revision:"e2a1c62e5062f6231c8e6207c65ba1e9"},{url:"/_next/static/media/4950f6c2f77d1f81.woff2",revision:"318d132edefe083939abb8427414d392"},{url:"/_next/static/media/49e496238c0612a6.woff2",revision:"a24f4d54212fc02dac69b61e220b0dd0"},{url:"/_next/static/media/53530a9af60864ce.woff2",revision:"2b9dfdd9a6c6a26d65d829dab85eea19"},{url:"/_next/static/media/5469c78a31546ae1.woff2",revision:"4ef0d210a97b3565d0440ce89733b0e4"},{url:"/_next/static/media/54d3411ffa848c7c.woff2",revision:"e35a880fa64098a0f239571dac42c0a5"},{url:"/_next/static/media/56fc4e9cf4b3a641.woff2",revision:"c7506d2fc97d6d4fbb9eff6a3c5f700e"},{url:"/_next/static/media/57f34d244d462b37.woff2",revision:"db2351aa388ebe57ad3e8c6ce2948a44"},{url:"/_next/static/media/59f9c5e9708097a4.woff2",revision:"867cc8299174639608831849ee488048"},{url:"/_next/static/media/5d61ce73b373d2d4.woff2",revision:"d23adc7340260ccca8a875bb0e72684b"},{url:"/_next/static/media/623a125f6ecaae02.woff2",revision:"2ad5eea946feee7658469be17b0d429a"},{url:"/_next/static/media/62fbf82aa4d407d2.woff2",revision:"3988c61077eb9e6e2e0f3feb6e068e5b"},{url:"/_next/static/media/6425e51b1cf41072.woff2",revision:"49c817f6668348bcced756330e05c9ac"},{url:"/_next/static/media/65a32121f1a8aaf5.woff2",revision:"f67c9d5a8eafad10d6014bd93c46b2ff"},{url:"/_next/static/media/65e43345f4789ba9.woff2",revision:"5067519741dd751a7514e5576d1dd7ed"},{url:"/_next/static/media/668abca418b300b1.woff2",revision:"9805c06bcbc2882efb5d6574f2399a6d"},{url:"/_next/static/media/66d4a9878d95ed0f.woff2",revision:"6da6e4d3dbc519b31f1c5ad8830fd872"},{url:"/_next/static/media/6aa36edd34f86e00.woff2",revision:"2f08babb4751903be2882e9c7838ba27"},{url:"/_next/static/media/6abceec8e4ff7d11.woff2",revision:"3847ee8f6fc652e34f846e0d61a9d513"},{url:"/_next/static/media/6be39a35b1c23726.woff2",revision:"00c1fe50ecf83c034100e330f3db40b2"},{url:"/_next/static/media/72b367164138feeb.woff2",revision:"23289e3cbcac4600c574e1ec2c15acc9"},{url:"/_next/static/media/7468820dcccbc5a3.woff2",revision:"e0a2349f6bcb2908daededdd08a67d53"},{url:"/_next/static/media/772507186cb91977.woff2",revision:"4fd1febfad8c51641bcd1a62b2644218"},{url:"/_next/static/media/77b8140dcb7b73ab.woff2",revision:"3c430cdfd09d45c16392c630e29701d6"},{url:"/_next/static/media/79c10c1ebd8dfd94.woff2",revision:"7e6fe2fd41d5174f1ed28fef1178a97f"},{url:"/_next/static/media/7c4de842e54a3922.woff2",revision:"dfed4cbccf0ac09aa48f9ada5bc8df48"},{url:"/_next/static/media/7d25ea25ee69d1e3.woff2",revision:"d1c000c058d9a5cce3a9088b0770b02c"},{url:"/_next/static/media/7ecb3c0cc147ef4c.woff2",revision:"dd79e21acc3253834d7f8e8b59fb203e"},{url:"/_next/static/media/8146aba97c412e75.woff2",revision:"12be1c3bd31855d26b0d668048ee0ad4"},{url:"/_next/static/media/819d4ac2d5291c27.woff2",revision:"a3448ea252eab0aef4947a0994f19fdb"},{url:"/_next/static/media/83e1d6b951e878d8.woff2",revision:"1bd1647f0fff08163a7cb66dca5096cd"},{url:"/_next/static/media/8e321b58cdfc165a.woff2",revision:"becb6e0fa286d8ca27254e14c341f8ef"},{url:"/_next/static/media/90e51316bfbe503f.woff2",revision:"088b3179eb605a20393d8bef5401c96c"},{url:"/_next/static/media/93d957d29b080afd.woff2",revision:"5ed22e1e2fe235957c0f14c46abb65d2"},{url:"/_next/static/media/9495c1e34bd36ab9.woff2",revision:"c4e88e87b8d07dd940bd50c240b5dc89"},{url:"/_next/static/media/986eb49dc2719504.woff2",revision:"a8482ad613456661ae9ae67efd0fc8da"},{url:"/_next/static/media/9ac2b7123152ba3c.woff2",revision:"e048d803ffd09e7bcfb8425c54a969f4"},{url:"/_next/static/media/9afae091435c2fef.woff2",revision:"78b431901d125eda3537f3680dc91786"},{url:"/_next/static/media/9f27fe6ead4bdeb4.woff2",revision:"c8a49a8fcc2b3cd0f105b6192eb09f88"},{url:"/_next/static/media/a1d1e207b1ffec74.woff2",revision:"aa1977102b2da4e4b3619286b6909e89"},{url:"/_next/static/media/a4a477a3efbfc823.woff2",revision:"46025747d94e28d0d6e0ac908f1c45a1"},{url:"/_next/static/media/a595562b1a03aff9.woff2",revision:"2d2e9dcac98aefe15493436ad3cced0a"},{url:"/_next/static/media/a6dc3ce1af2a6eef.woff2",revision:"3a8b0419b9cac1171aa3206c9d99160c"},{url:"/_next/static/media/ac519739450a7ae1.woff2",revision:"4553f25b0751acc92b00affeb874b465"},{url:"/_next/static/media/ad6b1cfa73d27e45.woff2",revision:"68b4e602f8fe53b2210a1bab16997fb3"},{url:"/_next/static/media/ae874482ca789924.woff2",revision:"8a58a6707eed50302b702a67a35c5db8"},{url:"/_next/static/media/b0c863192e6c620e.woff2",revision:"82b7d102fd85a7ff6a8137d6f819f323"},{url:"/_next/static/media/b46c15cbc84d8375.woff2",revision:"56faa8a613bf1b980e1f708319669b93"},{url:"/_next/static/media/b6eaf151ceeb71d0.woff2",revision:"ea854cca2847170d9a140347a3f9c828"},{url:"/_next/static/media/b7091f01641b13a0.woff2",revision:"bba430a0344f0e1d476d84e198151553"},{url:"/_next/static/media/b8fe2d5ffd8fb1eb.woff2",revision:"e52ea481bff0f277155d39f8b8b8fab4"},{url:"/_next/static/media/b96a9b174782f471.woff2",revision:"cddaa8cf0883269eb8d438b154271546"},{url:"/_next/static/media/bb869b07c9230943.woff2",revision:"7fca35a979c79aa765ea0476f765cf8d"},{url:"/_next/static/media/c1490452cf4026bb.woff2",revision:"da5c9f2eb7d16ecca235aa9e4989d25f"},{url:"/_next/static/media/c582a81411babd82.woff2",revision:"83376409e7a73d3cfc0257ddc6250309"},{url:"/_next/static/media/c873f85e24fdca82.woff2",revision:"232879463498a741a6074388edeee65e"},{url:"/_next/static/media/c96974d02952f99a.woff2",revision:"265cabf59dae6625c150f1dc065d4952"},{url:"/_next/static/media/ca0a8eab68de6e7f.woff2",revision:"86275b7fa53f864de52b81326bc4c675"},{url:"/_next/static/media/ca2aeacae963b561.woff2",revision:"d663c4839126039d119de2a7a3b63545"},{url:"/_next/static/media/d0d525ef3669ba3f.woff2",revision:"87ead86dd9f1a6f3e7f9c07322de6156"},{url:"/_next/static/media/d25c8a0462c4527d.woff2",revision:"7996289e7c6f99994566bb743f963292"},{url:"/_next/static/media/d2afb9ddb1693332.woff2",revision:"b2b52106edb11b51384d401e839b4a31"},{url:"/_next/static/media/d50ede6ee04c83b6.woff2",revision:"42fac51575bfea7b2abc20cb357d2f87"},{url:"/_next/static/media/db2b95ce015e8395.woff2",revision:"c1b3cc19b74a865856d297d1b0862880"},{url:"/_next/static/media/dfb66f65afa569ec.woff2",revision:"24940ec033c958206747f9aa35012e55"},{url:"/_next/static/media/e124c175004e3d90.woff2",revision:"f0f2ef592e94231bfa4b400640595915"},{url:"/_next/static/media/e1e8964d2a78b4eb.woff2",revision:"43934984c3dffe97ae67edbd0e49dc46"},{url:"/_next/static/media/e1f5a413e905e7ad.woff2",revision:"c52aaaecfa2ee8a3ba8350f9ea6aadf8"},{url:"/_next/static/media/e38e5d3706de9d28.woff2",revision:"f3bba2d6597074316b34e0c3a493911d"},{url:"/_next/static/media/e45cdb96a5e6c159.woff2",revision:"89bcef9413b1671483b252804bc10662"},{url:"/_next/static/media/e613a75aa604d538.woff2",revision:"44aa4276324b74da722c8825f61a6871"},{url:"/_next/static/media/e67074b0fb4af46c.woff2",revision:"ed9acb62ff245c9a0a61c478e0b84e19"},{url:"/_next/static/media/e80eccaab343d587.woff2",revision:"056a366595f6949d57a0119eb7f03ea3"},{url:"/_next/static/media/e9a7f54f117d1aee.woff2",revision:"40866302d5d1c9cb8ea37039b1dfd979"},{url:"/_next/static/media/ea0c9ae8035653df.woff2",revision:"714a63eb3816336fc861042e6c1176a5"},{url:"/_next/static/media/ee73278e616bac8e.woff2",revision:"622ffc5779e120957315c962da1f0840"},{url:"/_next/static/media/f0e7541445e9ad5b.woff2",revision:"ba5e9d632b9614fd1c29602d789950da"},{url:"/_next/static/media/f145b6c03516d551.woff2",revision:"c45f6a24130a43b44f14f8c793ba14ff"},{url:"/_next/static/media/f1ff28acc646e894.woff2",revision:"dfe4008134e5de64e3a9d2eb678b7d55"},{url:"/_next/static/media/f3157d326d792003.woff2",revision:"36077922c28bf6349014c2ee8622e187"},{url:"/_next/static/media/f3f1d528c7dee04a.woff2",revision:"fe892731f92d9a5282ca7c2871ff458d"},{url:"/_next/static/media/f5240b796002b3eb.woff2",revision:"3cd07a7b544a9a307de058bb4e66aa08"},{url:"/_next/static/media/fdaf8b6e949ad2dd.woff2",revision:"d44f01f609ef1477059faa17315771b5"},{url:"/_next/static/media/fe342eed832a4b3e.woff2",revision:"aa73d1666c78dc8ada8a2f7027976fb7"},{url:"/_next/static/media/googlelogo.fbcfcead.webp",revision:"769beed8739757835ab1f6c27cc44afd"},{url:"/_next/static/media/kakaologo.bacdad56.png",revision:"eef595f6a5073f7dcf6d724858074530"},{url:"/_next/static/media/naverlogo.7a9fa8a1.png",revision:"9b8bbec2b446ff566ce2df35bc2d7905"},{url:"/_offline",revision:"SvZZugCeYTn2kDTQtIrpg"},{url:"/favicon.ico",revision:"c30c7d42707a47a3f4591831641e50dc"},{url:"/icons/android-chrome-192x192.png",revision:"49a231542994a4cb9f19f6f5ebc43463"},{url:"/img/blur.png",revision:"a2d7c35d329a631df6556e9cc8548279"},{url:"/img/googlelogo.webp",revision:"769beed8739757835ab1f6c27cc44afd"},{url:"/img/kakaologo.png",revision:"eef595f6a5073f7dcf6d724858074530"},{url:"/img/myimg.png",revision:"5a88a5fcf81c85164d85569ad0befeb6"},{url:"/img/naverlogo.png",revision:"9b8bbec2b446ff566ce2df35bc2d7905"},{url:"/manifest.json",revision:"c4a834c01a07b1e6554b03937d2b1fcc"},{url:"/vercel.svg",revision:"4b4f1876502eb6721764637fe5c41702"}],{ignoreURLParametersMatching:[]}),e.cleanupOutdatedCaches(),e.registerRoute("/",new e.NetworkFirst({cacheName:"start-url",plugins:[{cacheWillUpdate:async({request:e,response:a,event:c,state:i})=>a&&"opaqueredirect"===a.type?new Response(a.body,{status:200,statusText:"OK",headers:a.headers}):a},{handlerDidError:async({request:e})=>self.fallback(e)}]}),"GET"),e.registerRoute(/^https:\/\/fonts\.(?:gstatic)\.com\/.*/i,new e.CacheFirst({cacheName:"google-fonts-webfonts",plugins:[new e.ExpirationPlugin({maxEntries:4,maxAgeSeconds:31536e3}),{handlerDidError:async({request:e})=>self.fallback(e)}]}),"GET"),e.registerRoute(/^https:\/\/fonts\.(?:googleapis)\.com\/.*/i,new e.StaleWhileRevalidate({cacheName:"google-fonts-stylesheets",plugins:[new e.ExpirationPlugin({maxEntries:4,maxAgeSeconds:604800}),{handlerDidError:async({request:e})=>self.fallback(e)}]}),"GET"),e.registerRoute(/\.(?:eot|otf|ttc|ttf|woff|woff2|font.css)$/i,new e.StaleWhileRevalidate({cacheName:"static-font-assets",plugins:[new e.ExpirationPlugin({maxEntries:4,maxAgeSeconds:604800}),{handlerDidError:async({request:e})=>self.fallback(e)}]}),"GET"),e.registerRoute(/\.(?:jpg|jpeg|gif|png|svg|ico|webp)$/i,new e.StaleWhileRevalidate({cacheName:"static-image-assets",plugins:[new e.ExpirationPlugin({maxEntries:64,maxAgeSeconds:86400}),{handlerDidError:async({request:e})=>self.fallback(e)}]}),"GET"),e.registerRoute(/\/_next\/image\?url=.+$/i,new e.StaleWhileRevalidate({cacheName:"next-image",plugins:[new e.ExpirationPlugin({maxEntries:64,maxAgeSeconds:86400}),{handlerDidError:async({request:e})=>self.fallback(e)}]}),"GET"),e.registerRoute(/\.(?:mp3|wav|ogg)$/i,new e.CacheFirst({cacheName:"static-audio-assets",plugins:[new e.RangeRequestsPlugin,new e.ExpirationPlugin({maxEntries:32,maxAgeSeconds:86400}),{handlerDidError:async({request:e})=>self.fallback(e)}]}),"GET"),e.registerRoute(/\.(?:mp4)$/i,new e.CacheFirst({cacheName:"static-video-assets",plugins:[new e.RangeRequestsPlugin,new e.ExpirationPlugin({maxEntries:32,maxAgeSeconds:86400}),{handlerDidError:async({request:e})=>self.fallback(e)}]}),"GET"),e.registerRoute(/\.(?:js)$/i,new e.StaleWhileRevalidate({cacheName:"static-js-assets",plugins:[new e.ExpirationPlugin({maxEntries:32,maxAgeSeconds:86400}),{handlerDidError:async({request:e})=>self.fallback(e)}]}),"GET"),e.registerRoute(/\.(?:css|less)$/i,new e.StaleWhileRevalidate({cacheName:"static-style-assets",plugins:[new e.ExpirationPlugin({maxEntries:32,maxAgeSeconds:86400}),{handlerDidError:async({request:e})=>self.fallback(e)}]}),"GET"),e.registerRoute(/\/_next\/data\/.+\/.+\.json$/i,new e.StaleWhileRevalidate({cacheName:"next-data",plugins:[new e.ExpirationPlugin({maxEntries:32,maxAgeSeconds:86400}),{handlerDidError:async({request:e})=>self.fallback(e)}]}),"GET"),e.registerRoute(/\.(?:json|xml|csv)$/i,new e.NetworkFirst({cacheName:"static-data-assets",plugins:[new e.ExpirationPlugin({maxEntries:32,maxAgeSeconds:86400}),{handlerDidError:async({request:e})=>self.fallback(e)}]}),"GET"),e.registerRoute((({url:e})=>{if(!(self.origin===e.origin))return!1;const a=e.pathname;return!a.startsWith("/api/auth/")&&!!a.startsWith("/api/")}),new e.NetworkFirst({cacheName:"apis",networkTimeoutSeconds:10,plugins:[new e.ExpirationPlugin({maxEntries:16,maxAgeSeconds:86400}),{handlerDidError:async({request:e})=>self.fallback(e)}]}),"GET"),e.registerRoute((({url:e})=>{if(!(self.origin===e.origin))return!1;return!e.pathname.startsWith("/api/")}),new e.NetworkFirst({cacheName:"others",networkTimeoutSeconds:10,plugins:[new e.ExpirationPlugin({maxEntries:32,maxAgeSeconds:86400}),{handlerDidError:async({request:e})=>self.fallback(e)}]}),"GET"),e.registerRoute((({url:e})=>!(self.origin===e.origin)),new e.NetworkFirst({cacheName:"cross-origin",networkTimeoutSeconds:10,plugins:[new e.ExpirationPlugin({maxEntries:32,maxAgeSeconds:3600}),{handlerDidError:async({request:e})=>self.fallback(e)}]}),"GET")}));
