<#--noinspection InjectedReferences-->
<#import '/spring.ftl' as spring>
<#import 'macros/forms/_forms.ftl' as forms>
<#import 'macros/image.ftl' as image>
<#import 'macros/table.ftl' as table>

<#function staticUrl url>
  <#return "/assets/${url}" >
</#function>


<#macro baseTemplate
  pageTitle=""
  showHeader=true
>
  <!DOCTYPE html>
  <html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>${pageTitle}</title>
    <link rel="stylesheet" href="${staticUrl("style.css")}">
    <link rel="icon" type="image/x-icon" href="https://spring.io/favicon.svg">
  </head>
    <body>
      <#if showHeader>
        <header><a href="/" class="site-title">Random Anime Picker</a></header>
      </#if>
      <main style="padding-top: ${showHeader?then("360", "400")}px;">
        <#nested>
      </main>
    </body>
  </html>
</#macro>
